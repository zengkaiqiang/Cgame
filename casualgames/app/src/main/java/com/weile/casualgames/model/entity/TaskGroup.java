package com.weile.casualgames.model.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zjj
 * 任务
 */
public class TaskGroup implements Serializable {
    String   name;
    String   group;
    List<Task> data;
    public List<Task> getData() {
        return data;
    }

    public void setData(List<Task> data) {
        this.data = data;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return group;
    }

    public void setType(String type) {
        this.group = type;
    }



}
