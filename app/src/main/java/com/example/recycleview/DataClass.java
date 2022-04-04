package com.example.recycleview;

import java.util.ArrayList;

public class DataClass {
    private String name;
    private int age;
    public static ArrayList<DataClass> dataClassList= new ArrayList<>();

    public DataClass(String name, int age){
        this.name=name;
        this.age=age;
    }
    public String getName(){
        return this.name;
    }
    public int getAge(){
        return this.age;
    }
    public void addToList(){
        dataClassList.add(0,this);
    }
}
