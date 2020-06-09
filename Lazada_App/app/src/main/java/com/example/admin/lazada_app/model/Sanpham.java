package com.example.admin.lazada_app.model;

import java.io.Serializable;

public class Sanpham implements Serializable {
    private int ID;
    private String tensanpham;
    private String mota;
    private String hinhanhsanpham;
    private Integer giasanpham;
    private int IDsanpham;

    public Sanpham() {
    }

    public Sanpham(int ID, String tensanpham, String mota, String hinhanhsanpham, Integer giasanpham, int IDsanpham) {
        this.ID = ID;
        this.tensanpham = tensanpham;
        this.mota = mota;
        this.hinhanhsanpham = hinhanhsanpham;
        this.giasanpham = giasanpham;
        this.IDsanpham = IDsanpham;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getTensanpham() {
        return tensanpham;
    }

    public void setTensanpham(String tensanpham) {
        this.tensanpham = tensanpham;
    }

    public String getMota() {
        return mota;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }

    public String getHinhanhsanpham() {
        return hinhanhsanpham;
    }

    public void setHinhanhsanpham(String hinhanhsanpham) {
        this.hinhanhsanpham = hinhanhsanpham;
    }

    public Integer getGiasanpham() {
        return giasanpham;
    }

    public void setGiasanpham(Integer giasanpham) {
        this.giasanpham = giasanpham;
    }

    public int getIDsanpham() {
        return IDsanpham;
    }

    public void setIDsanpham(int IDsanpham) {
        this.IDsanpham = IDsanpham;
    }
}
