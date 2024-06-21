package com.xiaomi.service;

public interface DataService {

    Long insert(String fileName);

    void delete(Long id);

    Long acquire(Long id);

    String rename(Long id);
}
