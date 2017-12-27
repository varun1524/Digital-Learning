package com.example.varun.signtuarecapture;

/**
 * Created by PANAM SHAH on 14-02-2015.
 */
public class Image {
    private int id;
    private String imgName;

    public Image(String s)
    {
        this.imgName=s;
    }

    public void setImgName(String imgName)
    {
        this.imgName=imgName;
    }


      public String getImgName()
    {
        return imgName;
    }

    //getters & setters

    @Override
    public String toString() {
        return "Image [id=" + id + ", name=" + imgName
                + "]";
    }

    public void setId(int id) {
        this.id = id;
}
    public int getId()
    {
        return id;
    }
}
