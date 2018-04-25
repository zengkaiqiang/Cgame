package com.weile.casualgames.game.model;

import java.io.Serializable;

/**
 * Created by zjj
 */
public class Player implements Serializable{
   private int id;
   private String name;
   private int header;
   private int flowers;
   private int grade;
   private String date;

   public Player(int id, String name, int header, int flowers, int grade){
       this.id = id;
       this.name = name;
       this.header = header;
       this.flowers = flowers;
       this.grade = grade;
   }

   public Player(String name, int header, String date){
        this.name = name;
        this.header = header;
        this.date = date;
   }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getHeader() {
        return header;
    }

    public int getFlowers(){
        return flowers;
    }

    public int getGrade(){
        return grade;
    }

    public String getDate() {
        return date;
    }

}
