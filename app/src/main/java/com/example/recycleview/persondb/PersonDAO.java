package com.example.recycleview.persondb;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface PersonDAO {
    //Select all
    @Query("Select * from person")
    List<personDataClass> getAll();

    //Select by many personID
    @Query("Select * from person where personId in (:personID)")
    List<personDataClass> getAllByIds(int[] personID);

    //Select by unique personID
    @Query("Select * from person where personId =:personID")
    List<personDataClass> getOneById(int personID);

    // Select by age
    @Query("Select * from person where personAge=:personAge")
    List<personDataClass> getAllByAge(int personAge);

    // Select by job
    @Query(("Select * from person where personJob=:personJob"))
    List<personDataClass> getAllByJob(int personJob);

    @Query("SELECT * FROM person ORDER BY personName ASC")
    LiveData<List<personDataClass>> getAllInOrder();

    @Query("Delete from person where personId=:personId")
    void deleteById(int personId);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertAll(personDataClass[] person);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(personDataClass person);

    @Update
    void update(personDataClass... person);

    @Delete
    void delete(personDataClass person);

}
