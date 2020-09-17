package com.listview.shopthoitrang.model;

import java.io.Serializable;

public class SanPham implements Serializable {
    public int id;
    public String name;
    public Long price;
    public String image;
    public String intro;
    public int feature;
    public int catid;

    public SanPham(int id, String name, Long price, String image, String intro, int feature, int catid) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.image = image;
        this.intro = intro;
        this.feature = feature;
        this.catid = catid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public int getFeature() {
        return feature;
    }

    public void setFeature(int feature) {
        this.feature = feature;
    }

    public int getCatid() {
        return catid;
    }

    public void setCatid(int catid) {
        this.catid = catid;
    }
}
