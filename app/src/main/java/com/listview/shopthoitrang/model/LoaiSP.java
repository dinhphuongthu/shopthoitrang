package com.listview.shopthoitrang.model;

public class LoaiSP {
    public int Id;
    public String Tensp;
    public String Imagesp;

    public LoaiSP(int id, String tensp, String imagesp) {
        Id = id;
        Tensp = tensp;
        Imagesp = imagesp;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getTensp() {
        return Tensp;
    }

    public void setTensp(String tensp) {
        Tensp = tensp;
    }

    public String getImagesp() {
        return Imagesp;
    }

    public void setImagesp(String imagesp) {
        Imagesp = imagesp;
    }
}
