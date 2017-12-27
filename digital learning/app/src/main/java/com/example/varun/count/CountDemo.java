package com.example.varun.count;

/**
 * Created by varun on 14/03/15.
 */
public class CountDemo {
    int id1,id2,id3,bg,v1,v2,v3,vbg;

    public CountDemo(int id1,int v1,int id2,int v2,int id3,int v3,int bg,int vbg){
        this.id1=id1;
        this.id2=id2;
        this.id3=id3;
        this.bg=bg;
        this.v1=v1;
        this.v2=v2;
        this.v3=v3;
        this.vbg=vbg;
    }

    public int getId1() {
        return id1;
    }

    public int getId2() {
        return id2;
    }

    public int getId3() {
        return id3;
    }

    public int getBg() {
        return bg;
    }

    public int getV1() {
        return v1;
    }

    public int getV2() {
        return v2;
    }

    public int getV3() {
        return v3;
    }

    public int getVbg() {
        return vbg;
    }

}
