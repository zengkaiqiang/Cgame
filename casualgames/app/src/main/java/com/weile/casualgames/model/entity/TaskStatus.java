package com.weile.casualgames.model.entity;

import java.io.Serializable;

/**
 * Created by zjj
 * 任务状态
 */
public class TaskStatus implements Serializable {
    long    id;
    String   taskId;
    String   total;
    String   type;
    String   state;
    String   source;
    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }


    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }


    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }


    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }




}
