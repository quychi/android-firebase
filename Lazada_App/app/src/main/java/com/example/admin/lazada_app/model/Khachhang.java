package com.example.admin.lazada_app.model;

import java.io.Serializable;

public class Khachhang implements Serializable {
    private String acount,pass,hoten,gmail,sdt,diachi;

    public Khachhang(String acount, String pass, String hoten, String gmail, String sdt, String diachi) {
        this.acount = acount;
        this.pass = pass;
        this.hoten = hoten;
        this.gmail = gmail;
        this.sdt = sdt;
        this.diachi = diachi;
    }

    public String getAcount() {
        return acount;
    }

    public void setAcount(String acount) {
        this.acount = acount;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getHoten() {
        return hoten;
    }

    public void setHoten(String hoten) {
        this.hoten = hoten;
    }

    public String getGmail() {
        return gmail;
    }

    public void setGmail(String gmail) {
        this.gmail = gmail;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }
}
