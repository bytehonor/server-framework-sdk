package com.bytehonor.sdk.basic.server.lock;

import com.ecwid.consul.v1.ConsulClient;
import com.ecwid.consul.v1.Response;
import com.ecwid.consul.v1.kv.model.GetValue;
import com.ecwid.consul.v1.kv.model.PutParams;
import com.ecwid.consul.v1.session.model.NewSession;
import com.ecwid.consul.v1.session.model.Session;

public class ConsulLock {
    public static final String KEY_PREFIX = "CONSUL_LOCK/"; // 同步锁参数前缀

    private ConsulClient consulClient;
    private String sessionId = null;
    private String keyPath; // 互斥锁、信号量存储在consul中的基础key路径

    /**
     * @param consulClient
     * @param lockKey      同步锁在consul的KV存储中的Key路径，会自动增加prefix前缀，方便归类查询
     * @param checkTtl     对锁Session的TTL
     */
    public ConsulLock(ConsulClient consulClient, String lockKey) {
        this.consulClient = consulClient;
        this.keyPath = KEY_PREFIX + lockKey;
    }

    /**
     * 创建session
     * 
     * @param sessionName
     * @return
     */
    private String createSession(String sessionName, int ttlSeconds) {
        NewSession newSession = new NewSession();
        newSession.setName(sessionName);
        newSession.setBehavior(Session.Behavior.DELETE); // 删除
        newSession.setTtl(ttlSeconds + "s");
        return consulClient.sessionCreate(newSession, null).getValue();
    }

    /**
     * 根据成员变量sessionId来销毁session
     */
    private void destroySession() {
        if (sessionId != null) {
            consulClient.sessionDestroy(sessionId, null);
            sessionId = null;
        }
    }

    /**
     * 获取同步锁
     *
     * @param block      是否阻塞，直到获取到锁为止，默认尝试间隔时间为100ms。
     * @param ttlSeconds 锁的生命周期，秒
     * @return
     */
    public Boolean lock(boolean block, int ttlSeconds) throws InterruptedException {
        return lock(block, ttlSeconds, 100L, 2);
    }

    /**
     * 获取同步锁
     *
     * @param block         是否阻塞，直到获取到锁为止
     * @param ttlSeconds    锁的生命周期，秒
     * @param intervalMills block=true时有效，再次尝试的间隔时间，毫秒
     * @param maxTimes      block=true时有效，最大尝试次数
     * @return
     */
    public boolean lock(boolean block, int ttlSeconds, long intervalMills, int maxTimes) throws InterruptedException {
        if (sessionId != null) {
            throw new RuntimeException(sessionId + " - Already locked!");
        }
        sessionId = createSession("lock-" + this.keyPath, ttlSeconds);
        int count = 1;
        while (true) {
            PutParams putParams = new PutParams();
            putParams.setAcquireSession(sessionId);
            if (consulClient.setKVValue(keyPath, "" + System.currentTimeMillis(), putParams).getValue()) {
                return true;
            } else if (block) {
                if (count >= maxTimes) {
                    return false;
                } else {
                    count++;
                    if (intervalMills > 1L) {
                        Thread.sleep(intervalMills);
                    }
                    continue;
                }
            } else {
                return false;
            }
        }
    }

    /**
     * 释放同步锁
     *
     * @return
     */
    public boolean unlock() {
        destroySession();
        return true;
    }

    /**
     * 获取锁的内容
     * 
     * @return
     */
    public long getLockTime() {
        Response<GetValue> res = consulClient.getKVValue(keyPath);
        long lockAt = 0L;
        if (res.getValue() == null) {
            return lockAt;
        }
        String val = res.getValue().getDecodedValue();
        try {
            lockAt = Long.valueOf(val);
        } catch (Exception e) {

        }
        return lockAt;
    }

}

