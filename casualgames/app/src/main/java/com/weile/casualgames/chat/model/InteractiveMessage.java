package com.weile.casualgames.chat.model;

/**
 * Created by zjj
 */
public class InteractiveMessage {
   private int id;
   private String name;
   private int resId;
   private String time;
   private String content;

   public InteractiveMessage(int id, int resId, String name, String content, String time){
       this.id = id;
       this.name = name;
       this.resId = resId;
       this.content = content;
       this.time = time;
   }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getResId(){
        return resId;
    }

    public String getContent(){
        return content;
    }

    public String getTime(){
        return time;
    }

}
