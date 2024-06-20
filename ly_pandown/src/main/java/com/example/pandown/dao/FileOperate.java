package com.example.pandown.dao;

import jakarta.persistence.*;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name="file")
public class FileOperate {
    @Id   //表示自增主键
    @Column(name="id")
    @GeneratedValue(strategy = IDENTITY)//表示是数据库生成的自增的id
    private long id;

    @Column(name="name")//将数据库的字段和本地的字段进行匹配，
    // 这里是一致的，大多数情况下不一致
    private String name;


    @Column(name="link")
    private String link;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
