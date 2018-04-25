package com.weile.casualgames.model.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zjj
 * 任务
 */
public class Task implements Serializable {
    long   id;
    String  headurl;
    String  linkUrl;
    String   title;
    String  targetTitle;
    String   type;
    String   target;
    int  source;
    String  section;
    com.weile.casualgames.model.entity.TaskStatus taskStatus;//添加
    String   maxtime;
    String   begintime;
    String   endtime;
    List<Reward> reward=new ArrayList<>();

    public String getEndtime() {
        return endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }

    public String getHeadurl() {
        return headurl;
    }

    public void setHeadurl(String headurl) {
        this.headurl = headurl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTargetTitle() {
        return targetTitle;
    }

    public void setTargetTitle(String targetTitle) {
        this.targetTitle = targetTitle;
    }

    public int getSource() {
        return source;
    }

    public void setSource(int source) {
        this.source = source;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getMaxtime() {
        return maxtime;
    }

    public void setMaxtime(String maxtime) {
        this.maxtime = maxtime;
    }

    public String getBegintime() {
        return begintime;
    }

    public void setBegintime(String begintime) {
        this.begintime = begintime;
    }



    public String getLinkUrl() {
        return linkUrl;
    }

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
    }
    public com.weile.casualgames.model.entity.TaskStatus getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(com.weile.casualgames.model.entity.TaskStatus taskStatus) {
        this.taskStatus = taskStatus;
    }



    public List<Reward> getReward() {
        return reward;
    }

    public void setReward(List<Reward> reward) {
        this.reward = reward;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getHeadUrl() {
        return headurl;
    }

    public void setHeadUrl(String headurl) {
        this.headurl = headurl;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }


}
