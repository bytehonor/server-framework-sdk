package com.bytehonor.sdk.server.spring.scheduler.work;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bytehonor.sdk.lang.spring.Java;

public final class ServerWorkFactory {

    private static final Logger LOG = LoggerFactory.getLogger(ServerWorkFactory.class);

    private final List<ServerWork> works;

    public ServerWorkFactory() {
        this.works = new ArrayList<ServerWork>();
    }

    public List<ServerWork> works() {
        return works;
    }

    public boolean isEmpty() {
        return works.isEmpty();
    }

    public ServerWorkFactory add(ServerWork work) {
        Java.requireNonNull(work, "work");

        works.add(work);
        return this;
    }

    public void run() {
        try {
            LOG.info("run works:{}", works.size());
            for (ServerWork work : works) {
                work.start();
            }
        } catch (Exception e) {
            LOG.error("run error", e);
        }
    }
}
