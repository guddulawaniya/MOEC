package com.example.moec.Room_database;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class userViewModel extends AndroidViewModel{
    private  fetchdata_Class repository;
    private LiveData<List<database_module>> liveData;


    public userViewModel(@NonNull Application application) {
        super(application);
        repository = new fetchdata_Class(application);
        liveData = repository.getLiveData();
    }

    public void insert(database_module user){
        repository.insert(user);
    }
    public void update(database_module user){
        repository.update(user);
    }
    public void delete(database_module user){
        repository.delete(user);
    }
    public void deleteAllUsers() {
        repository.deleteAllUsers();
    }

    public LiveData<List<database_module>> getLiveData() {
        return liveData;
    }
}
