package com.weile.casualgames.game.model;

/**
 * Created by zjj
 */
public class GameMenu {
   private int id;
   private int name;
   private int resId;

   public GameMenu(int id, int resId, int name){
       this.id = id;
       this.name = name;
       this.resId = resId;
   }

    public int getId() {
        return id;
    }

    public int getName() {
        return name;
    }

    public int getResId(){
        return resId;
    }

}
