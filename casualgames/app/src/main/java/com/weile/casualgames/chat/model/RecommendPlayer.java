package com.weile.casualgames.chat.model;

/**
 * Created by zjj
 */
public class RecommendPlayer {
   private int id;
   private String name;
   private int resId;

   public RecommendPlayer(int id, int resId, String name){
       this.id = id;
       this.name = name;
       this.resId = resId;
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

}
