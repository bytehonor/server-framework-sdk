package com.bytehonor.sdk.server.spring.job;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.util.CollectionUtils;

import com.bytehonor.sdk.lang.spring.exception.SpringLangException;
import com.bytehonor.sdk.lang.spring.thread.LoopIntervalThread;

/**
 * 竞争任务线程
 * 
 * @author lijianqiang
 *
 */
public class SpringJobThread {

    private final LoopIntervalThread thread;

    private SpringJobThread(LoopIntervalThread thread) {
        this.thread = thread;
    }

    public void start() {
        thread.start();
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private String name;
        private SpringJobLocker locker;
        private SpringJobConfig config;
        private List<SpringJob> jobs;

        private Builder() {
            this.config = new SpringJobConfig();
            this.jobs = new ArrayList<SpringJob>();
        }

        public Builder job(SpringJob job) {
            Objects.requireNonNull(job, "job");

            this.jobs.add(job);
            return this;
        }

        public Builder name(String name) {
            Objects.requireNonNull(name, "name");

            this.name = name;
            return this;
        }

        public Builder locker(SpringJobLocker locker) {
            Objects.requireNonNull(locker, "locker");

            this.locker = locker;
            return this;
        }

        public Builder config(SpringJobConfig config) {
            Objects.requireNonNull(config, "config");

            this.config = config;
            return this;
        }

        public SpringJobThread build() {
            Objects.requireNonNull(name, "name");
            Objects.requireNonNull(locker, "locker");
            Objects.requireNonNull(config, "config");

            if (CollectionUtils.isEmpty(jobs)) {
                throw new SpringLangException("jobs empty");
            }

            return new SpringJobThread(new LoopIntervalThread(SpringJobThread.class.getSimpleName(),
                    new SpringJobTask(name, locker, config, jobs)));
        }
    }
}
