package com.module.share;

/**
 * 分享实体
 * Created by zjj
 */
public class Share {

    String text;
    String title;
    String targetUrl;
    public Share() {
    }
    public Share(String title, String text, String targetUrl) {
        this.text = text;
        this.title = title;
        this.targetUrl = targetUrl;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTargetUrl() {
        return targetUrl;
    }

    public void setTargetUrl(String targetUrl) {
        this.targetUrl = targetUrl;
    }
}
