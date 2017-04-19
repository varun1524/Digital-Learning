package com.example.varun.matchcolor;

import android.graphics.Color;

/**
 * Created by PANAM SHAH on 26-02-2015.
 */
public class Fruit
{

    public int color1,color2,color3,color4;
    public int path,colorName;



    public Fruit(String color1,String color2,String color3,String correctAnsColor,int path,int colorName){

        this.color1= Color.parseColor(color1);
        this.color2= Color.parseColor(color2);
        this.color3= Color.parseColor(color3);
        this.color4= Color.parseColor(correctAnsColor);
        this.path=path;
        this.colorName=colorName;


    }

    public void setColor1(int c1) {
        this.color1=c1;
    }

    public void setColor2(int c2) {
        this.color2=c2;
    }
    public void setColor3(int c3) {
        this.color3=c3;
    }
    public void setColor4(int color4) {
        this.color4 = color4;
    }

    public void setPath(int path) {
        this.path = path;
    }





    public int getColor1() {
        return color1;
    }


    public int getColor2() {
        return color2;
    }

    public int getColor3() {
        return color3;
    }


    public int getColor4() {
        return color4;
    }

    public int getPath()
    {
        return path;
    }


    public int getColorName() {
        return colorName;
    }
}
