package com.example.recycleview.persondb;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class PersonRepository {
    private PersonDAO personDAO;
    private PersonRoomDataBase db;
    private LiveData<List<personDataClass>> mPerson;


    public PersonRepository(Application application){
        db=PersonRoomDataBase.getDatabase(application);
        personDAO=db.personDAO();
        mPerson=personDAO.getAllInOrder();
    }

    // get private field
    public LiveData<List<personDataClass>> getAllInOrder() {
        return mPerson;
    }

    public List<personDataClass> getAll() {
        return personDAO.getAll();
    }

    // Insert
    public void insertPerson(personDataClass person){
        PersonRoomDataBase.databaseWriteExecutor.execute(()->personDAO.insert(person));
    }

    //Delete
    public void deletePerson(personDataClass person){
        PersonRoomDataBase.databaseWriteExecutor.execute(()->personDAO.delete(person));
    }

    //Delete
    public void deletePersonById(int id){
        PersonRoomDataBase.databaseWriteExecutor.execute(()->personDAO.deleteById(id));
    }
}
