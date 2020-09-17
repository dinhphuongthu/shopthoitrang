package com.listview.shopthoitrang.model;

public class GioHang {
    public int idsp;
    public String tensp;
    public Long giasp;
    public String hinhsp;
    public int soluongsp;

    public GioHang(int idsp, String tensp, Long giasp, String hinhsp, int soluongsp) {
        this.idsp = idsp;
        this.tensp = tensp;
        this.giasp = giasp;
        this.hinhsp = hinhsp;
        this.soluongsp = soluongsp;
    }
    public int getIdsp() {
        return idsp;
    }

    public void setIdsp(int idsp) {
        this.idsp = idsp;
    }

    public String getTensp() {
        return tensp;
    }

    public void setTensp(String tensp) {
        this.tensp = tensp;
    }

    public Long getGiasp() {
        return giasp;
    }

    public void setGiasp(Long giasp) {
        this.giasp = giasp;
    }

    public String getHinhsp() {
        return hinhsp;
    }

    public void setHinhsp(String hinhsp) {
        this.hinhsp = hinhsp;
    }

    public int getSoluongsp() {
        return soluongsp;
    }

    public void setSoluongsp(int soluongsp) {
        this.soluongsp = soluongsp;
    }







}
