package com.example.admin.lazada_app.model;

import java.io.Serializable;

public class NhanXet implements Serializable {
    private int id;
    private int idSP;
    private String accountKH;
    private String review;

    public NhanXet() {
    }

    public NhanXet(int id, int idSP, String accountKH, String review) {
        this.id = id;
        this.idSP = idSP;
        this.accountKH = accountKH;
        this.review = review;
    }

    public NhanXet(int idSP, String accountKH, String review) {
        this.idSP = idSP;
        this.accountKH = accountKH;
        this.review = review;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdSP() {
        return idSP;
    }

    public void setIdSP(int idSP) {
        this.idSP = idSP;
    }

    public String getAccountKH() {
        return accountKH;
    }

    public void setAccountKH(String accountKH) {
        this.accountKH = accountKH;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }
}
