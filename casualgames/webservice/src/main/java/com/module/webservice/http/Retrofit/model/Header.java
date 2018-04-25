package com.module.webservice.http.Retrofit.model;

import java.io.Serializable;

/**
 * Created by zjj
 */

public class Header implements Serializable{
    public static String ticket;
    public static String getTicket() {
        if (ticket==null){
            return "";
        }
        return ticket;
    }

    public static void setTicket(String ticket) {
        Header.ticket = ticket;
    }



}
