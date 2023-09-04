package com.example.moec.Room_database;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class fetchdata_Class {
    private  SearchDao userDao;

    private LiveData<List<database_module>> liveData;

    public fetchdata_Class(Application application) {
        AppDatabase database = AppDatabase.getInstance(application);
        userDao = database.userDao();
        liveData = userDao.getAlldata();
    }

    public void insert(database_module user){
        new InsertNoteAyncTask(userDao).execute(user);

    }
    public void search_data(database_module user){
        new finddata_updateNoteAyncTask(userDao).execute(user);

    }
    public void update(database_module user){
        new updateNoteAyncTask(userDao).execute(user);

    }
    public void delete(database_module user){
        new deleteNoteAyncTask(userDao).execute(user);

    }
    public void deleteAllUsers(){
        new deleteAllNoteAyncTask(userDao).execute();

    }

    public  LiveData<List<database_module>> getLiveData() {
        return liveData;
    }

    private static  class  InsertNoteAyncTask  extends AsyncTask<database_module,Void,Void>{
        private  SearchDao userDao;

        public InsertNoteAyncTask(SearchDao userDao) {
            this.userDao = userDao;
        }

        @Override
        protected Void doInBackground(database_module... users) {
            userDao.insert(users[0]);
            return null;
        }
    }
    private static  class  finddata_updateNoteAyncTask  extends AsyncTask<database_module,Void,Void>{
        private  SearchDao userDao;

        public finddata_updateNoteAyncTask(SearchDao userDao) {
            this.userDao = userDao;
        }

        @Override
        protected Void doInBackground(database_module... users) {
            userDao.update(users[0]);
            return null;
        }
    }
    private static  class  updateNoteAyncTask  extends AsyncTask<database_module,Void,Void>{
        private  SearchDao userDao;

        public updateNoteAyncTask(SearchDao userDao) {
            this.userDao = userDao;
        }

        @Override
        protected Void doInBackground(database_module... users) {
            userDao.update(users[0]);
            return null;
        }
    }

    private static  class  deleteNoteAyncTask  extends AsyncTask<database_module,Void,Void>{
        private  SearchDao userDao;

        public deleteNoteAyncTask(SearchDao userDao) {
            this.userDao = userDao;
        }

        @Override
        protected Void doInBackground(database_module... users) {
            userDao.delete(users[0]);
            return null;
        }
    }
    private static  class  deleteAllNoteAyncTask  extends AsyncTask<database_module,Void,Void>{
        private  SearchDao userDao;

        public deleteAllNoteAyncTask(SearchDao userDao) {
            this.userDao = userDao;
        }

        @Override
        protected Void doInBackground(database_module... users) {
            userDao.deleteAllUsers();
            return null;
        }
    }
}
