package com.example.recycleview;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.recycleview.persondb.PersonRepository;
import com.example.recycleview.persondb.personDataClass;

import java.util.List;

public class PersonViewModel extends AndroidViewModel{

    private PersonRepository mRepository;
    private LiveData<List<personDataClass>> mAllPerson;

    public PersonViewModel (Application application) {
        super(application);
        mRepository = new PersonRepository(application);
        mAllPerson = mRepository.getAllInOrder();
    }

    //Get private field
    public LiveData<List<personDataClass>> getAllPerson() { return mAllPerson; }

    public List<personDataClass> getAllPersonList() { return mRepository.getAll(); }

    public void insert(personDataClass person) { mRepository.insertPerson(person);
        Log.d("Data List DAO",person.getName());
    }

    public void delete(personDataClass person) { mRepository.deletePerson(person); }

}
