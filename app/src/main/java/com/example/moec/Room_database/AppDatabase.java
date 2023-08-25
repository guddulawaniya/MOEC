package com.example.moec.Room_database;


import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;


@Database(entities = {database_module.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase instance;

    public abstract SearchDao userDao();

    public static synchronized AppDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    AppDatabase.class, "search_database")
                    .allowMainThreadQueries()
                    .addCallback(callback)
                    .build();
        }
        return instance;
    }

    private static Callback callback = new Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            new populateAsyncTask(instance).execute();
        }
    };

    private static class populateAsyncTask extends AsyncTask<Void, Void, Void> {

        private SearchDao userDao;

        public populateAsyncTask(AppDatabase db) {
            userDao = db.userDao();
        }
        @Override
        protected Void doInBackground(Void... voids) {
        /*    userDao.insert(new database_module("guddu"));
            userDao.insert(new database_module("guddu"));
            userDao.insert(new database_module("guddu"));
            userDao.insert(new database_module("guddu"));*/

            return null;
        }
    }
}