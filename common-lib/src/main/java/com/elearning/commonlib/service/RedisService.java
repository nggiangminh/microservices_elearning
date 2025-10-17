package com.elearning.commonlib.service;

public interface RedisService {
    void set(String key, Object value, long time);
    void set(String key, Object value);
    Object get(String key);
    Boolean del(String key);
}
