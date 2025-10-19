package com.bytehonor.sdk.framework.server.scheduler.work;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bytehonor.sdk.framework.lang.Java;

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

    public void play() {
        try {
            LOG.info("play works:{}", works.size());
            for (ServerWork work : works) {
                work.schedule();
            }
        } catch (Exception e) {
            LOG.error("play error", e);
        }
    }
}
