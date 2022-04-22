package com.example.recycleview.persondb;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.ArrayList;

@Entity (tableName = "person")
public class personDataClass {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "personId")
    private int id;

    @NonNull
    @ColumnInfo(name="personName")
    private String name;

    @NonNull
    @ColumnInfo(name="personAge")
    private int age;

    @NonNull
    @ColumnInfo(name="personJob")
    private String job;
    public static ArrayList<personDataClass> personDataClassList = new ArrayList<>();

    public personDataClass(String name, int age, String job){
        this.id=id;
        this.name=name;
        this.age=age;
        this.job=job;
    }

    public void setId(@NonNull int id){
        this.id=id;
    }
    public String getName(){
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getAge(){
        return this.age;
    }

    public int getId(){
        return this.id;
    }

    public String getJob(){
        return this.job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public void addToList(){
        personDataClassList.add(0,this);
    }
}
