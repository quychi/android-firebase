package com.example.admin.lazada_app.model;

public class Loaisp {
    private int id;
    private String tensp;
    private String hinhanhsp;

    public Loaisp(int id, String tensp, String hinhanhsp) {

        this.id = id;
        this.tensp = tensp;
        this.hinhanhsp = hinhanhsp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTensp() {
        return tensp;
    }

    public void setTensp(String tensp) {
        this.tensp = tensp;
    }

    public String getHinhanhsp() {
        return hinhanhsp;
    }

    public void setHinhanhsp(String hinhanhsp) {
        this.hinhanhsp = hinhanhsp;
    }
}
