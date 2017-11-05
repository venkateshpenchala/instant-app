package com.fraunhofer.fraunhofer.data.model;

/**
 * Created by Venkatesh on 6/12/2017.
 */

public class DataModel {
    public String name;
    public String id;
    public String kategory;
    public String info;
    public String references;
    public String other;
    public String image;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKategory() {
        return kategory;
    }

    public void setKategory(String kategory) {
        this.kategory = kategory;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getReferences() {
        return references;
    }

    public void setReferences(String references) {
        this.references = references;
    }


    public String getOther() {
        return other;
    }

    public void setOther(String other) { this.other = other; }

    public String getImage() {
        return image;
    }

    public void setImage(String image) { this.image = image; }
}
