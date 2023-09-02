package com.example.moec.Room_database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;
@Dao
public interface SearchDao {
    @Query("SELECT * FROM search_Text")
    LiveData<List<database_module>> getAlldata();

    @Query("SELECT * FROM search_Text WHERE searchtext LIKE '%' || :searchQuery || '%'")
    LiveData<List<database_module>> searchNotes(String searchQuery);


    @Insert()
    void insert(database_module user);
    @Update
    void update(database_module user);

    @Delete
    void delete(database_module user);

    @Query("DELETE FROM search_Text")
    void deleteAllUsers();

}
