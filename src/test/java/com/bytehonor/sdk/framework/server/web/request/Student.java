package com.bytehonor.sdk.framework.server.web.request;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Student {

    private static final Logger LOG = LoggerFactory.getLogger(Student.class);

    private Long id;

    private String nickname;

    private Integer age;

    private Long updateAt;

    private Long createAt;

    public static String hello(String msg) {
        LOG.info("hello {}", msg);
        return "hello" + msg;
    }
    
    public static String hi(String msg) {
        LOG.info("hi {}", msg);
        return "hi" + msg;
    }
    
    public String repeat(String msg) {
        return nickname + "repeat" + msg;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Long getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Long updateAt) {
        this.updateAt = updateAt;
    }

    public Long getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Long createAt) {
        this.createAt = createAt;
    }

}
