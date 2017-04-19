package com.example.varun.digitallearning;

/**
 * Created by Dell on 24-03-2015.
 */
public class User {
    private String std,pass,imgPath;
    private String name,age;
    private int id;

    public User(String s){}

    public User( String name,String std,String age,String pass,String imgPath) {
        super();

        this.name = name;
        this.std=std;
        this.age=age;
        this.pass=pass;
        this.imgPath=imgPath;
    }



    //getters & setters

    @Override
    public String toString() {
        return "User [id=" + id + ", name=" + name + ", std=" + std + ",age=" + age +",pass=" + pass + ",imgPath=" +imgPath
                + "]";
    }

    public void setId(int id) {
        this.id = id;
    }




    public void setName(String name) {
        this.name = name;
    }

    public void setStd(String std) {
        this.std = std;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public void setImgPath(String imgPath)
    {
        this.imgPath=imgPath;
    }

    public int getId() {
        return id;
    }

    public String getPass() {
        return pass;
    }

    public String getName() {
        return name;
    }

    public String getStd() {
        return std;
    }

    public String getAge() {
        return age;
    }

    public String getImgPath()
    {
        return imgPath;
    }


}