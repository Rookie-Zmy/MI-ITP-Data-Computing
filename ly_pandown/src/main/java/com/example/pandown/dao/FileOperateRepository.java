package com.example.pandown.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface FileOperateRepository extends JpaRepository<FileOperate,Long> {

    List<FileOperate> findByName(String name);

    void deleteByName(String name);
}
