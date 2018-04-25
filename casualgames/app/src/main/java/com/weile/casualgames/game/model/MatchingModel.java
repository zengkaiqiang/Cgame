package com.weile.casualgames.game.model;

import com.weile.casualgames.login.model.UserInfo;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zjj
 */
public class MatchingModel implements Serializable{
   private String Key;
   private String Ip;
   private List<UserInfo> Users;
   private int Id;


   public int getId(){
       return Id;
   }

    public String getKey() {
        return Key;
    }

    public String getIp() {
        return Ip;
    }

    public List<UserInfo> getUsers(){
        return Users;
    }

}
