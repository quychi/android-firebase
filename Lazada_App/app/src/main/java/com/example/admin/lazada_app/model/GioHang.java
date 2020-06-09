package com.example.admin.lazada_app.model;

import java.io.Serializable;

public class GioHang implements Serializable {
    private int idsp;
    private String Tensp;
    private int soluong;
    private String Hinhanhsp;
    private long giamoi;
    private Khachhang kh;
    private Sanpham sp;

    public GioHang() {
    }

    public GioHang(int idsp, String tensp, int soluong, String hinhanhsp, long giamoi) {
        this.idsp = idsp;
        Tensp = tensp;
        this.soluong = soluong;
        Hinhanhsp = hinhanhsp;
        this.giamoi = giamoi;
    }

    public int getIdsp() {
        return idsp;
    }

    public void setIdsp(int idsp) {
        this.idsp = idsp;
    }

    public String getTensp() {
        return Tensp;
    }

    public void setTensp(String tensp) {
        Tensp = tensp;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public String getHinhanhsp() {
        return Hinhanhsp;
    }

    public void setHinhanhsp(String hinhanhsp) {
        Hinhanhsp = hinhanhsp;
    }

    public long getGiamoi() {
        return giamoi;
    }

    public void setGiamoi(long giamoi) {
        this.giamoi = giamoi;
    }
}
