package com.example.a14779.codeeditor.Model;

import java.io.File;

/**
 * Created by liangtao on 18-2-6.
 */

public class FileInfo {

    /**
     * name : test
     * type : .c
     * createTime : 2018-01-26 12:12:12
     * lastTime : 2018-01-26 12:24:24
     * size : 102455577
     */

    private String name;
    private String type;
    private String createTime;
    private String lastTime;
    private int size;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getLastTime() {
        return lastTime;
    }

    public void setLastTime(String lastTime) {
        this.lastTime = lastTime;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
