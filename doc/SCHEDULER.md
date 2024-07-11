# scheduler

20240711

两种

public和private

public是集群内每次执行者随机，每次锁抢占临时决定

private是集群内启动时锁抢占一次，以后固定属于初始抢占者