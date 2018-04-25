package com.weile.casualgames.model.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zjj
 * 任务
 */
public class TaskStatusGroup implements Serializable {
    String   name;
    String   group;
    List<TaskStatus> data;
    public List<TaskStatus> getData() {
        return data;
    }

    public void setData(List<TaskStatus> data) {
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
