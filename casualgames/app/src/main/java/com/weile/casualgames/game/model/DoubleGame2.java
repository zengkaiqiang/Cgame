package com.weile.casualgames.game.model;

public class DoubleGame2 {

    private String Ip;
    private int Id;
    private int Num;
    private String Name;
    private String Img;
    private String C1;
    private String C2;

    public DoubleGame2() {
        super();
    }

    public DoubleGame2(String Ip, int Id, int Num, String Name, String Img, String C1, String C2) {
        super();
        this.Ip = Ip;
        this.Id = Id;
        this.Num = Num;
        this.Name = Name;
        this.Img = Img;
        this.C1 = C1;
        this.C2 = C2;

    }

    public String getIp() {
        return Ip;
    }

    public void setIp(String ip) {
        Ip = ip;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getNum() {
        return Num;
    }

    public void setNum(int num) {
        Num = num;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getImg() {
        return Img;
    }

    public void setImg(String img) {
        Img = img;
    }

    public String getC1() {
        return C1;
    }

    public void setC1(String c1) {
        C1 = c1;
    }

    public String getC2() {
        return C2;
    }

    public void setC2(String c2) {
        C2 = c2;
    }

    @Override
    public String toString() {
        return "DoubleGame2{" +
                "Ip='" + Ip + '\'' +
                ", Id=" + Id +
                ", Num=" + Num +
                ", Name='" + Name + '\'' +
                ", Img='" + Img + '\'' +
                ", C1='" + C1 + '\'' +
                ", C2='" + C2 + '\'' +
                '}';
    }
}
