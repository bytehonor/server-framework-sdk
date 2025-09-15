package com.bytehonor.sdk.server.spring.scheduler.work;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import com.bytehonor.sdk.lang.spring.Java;
import com.bytehonor.sdk.lang.spring.string.SpringString;
import com.bytehonor.sdk.server.spring.scheduler.work.lock.SpringWorkLocker;

/**
 * 集群模式，一个server独占一个group
 * 
 * @author lijianqiang
 *
 */
public class ClusterGroupFactory {

    private static final Logger LOG = LoggerFactory.getLogger(ClusterGroupFactory.class);

    private final long locks;
    private final List<ClusterGroup> groups;
    private SpringWorkLocker locker;
    private String server;
    private String subject;

    public ClusterGroupFactory(long locks) {
        this.locks = locks;
        this.groups = new ArrayList<ClusterGroup>();
        this.subject = "";
        this.server = "";
    }
    
    public void init(String server, SpringWorkLocker locker) {
        Java.requireNonNull(server, "server");
        Java.requireNonNull(locker, "locker");
        
        this.server = server;
        this.locker = locker;
    }

    public ClusterGroupFactory add(ClusterGroup group) {
        Java.requireNonNull(group, "group");

        LOG.info("add subject:{}", group.subject());

        if (SpringString.isEmpty(group.subject()) == false) {
            groups.add(group);
        }

        return this;
    }

    public void process() {
        prepare();
        applyWork();
        keepAlive();
        checkIdle();
    }
    
    private void prepare() {
        if (SpringString.isEmpty(server)) {
            throw new RuntimeException("server invalid");
        }
        
        if (locker == null) {
            throw new RuntimeException("locker invalid");
        }
    }

    private void applyWork() {
        if (SpringString.isEmpty(subject) == false) {
            return;
        }

        if (CollectionUtils.isEmpty(groups)) {
            LOG.warn("groups empty");
            return;
        }

        for (ClusterGroup group : groups) {
            if (locker.lock(group.subject(), server, locks) == false) {
                continue;
            }

            // 只启动一次
            gotAndStart(group);
            break;
        }
    }
    
    private void gotAndStart(ClusterGroup group) {
        subject = group.subject();
        LOG.info("gotAndStart subject:{}, server:{}", subject, server);
        group.factory().run();
    }
    
    private void keepAlive() {
        if (SpringString.isEmpty(subject)) {
            LOG.debug("server:{} keepAlive end, subject empty", server);
            return;
        }

        String which = locker.which(subject);
        boolean success = Java.equals(which, server);
        LOG.info("server:{} keepAlive success:{}, subject:{}", server, success, subject);
        if (success) {
            locker.expireAt(subject, System.currentTimeMillis() + locks);
        }
    }

    private void checkIdle() {
        if (CollectionUtils.isEmpty(groups)) {
            return;
        }

        for (ClusterGroup group : groups) {
            if (SpringString.isEmpty(locker.which(group.subject()))) {
                LOG.warn("server:{} checkIdle subject:{} no worker", server, group.subject());
            }
        }
    }

    public boolean isEmpty() {
        return groups.isEmpty();
    }
}
