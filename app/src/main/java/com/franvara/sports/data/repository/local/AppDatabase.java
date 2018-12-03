package com.franvara.sports.data.repository.local;


import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.franvara.sports.data.data_model.entities.PlayerEntity;
import com.franvara.sports.data.repository.local.daos.PlayerDao;

@Database(entities = {PlayerEntity.class}, version = 1)
abstract class AppDatabase extends RoomDatabase {

    private static final String DATABASE_NAME = "sports";
    private static final Object LOCK = new Object();

    private static volatile AppDatabase sInstance;


    static AppDatabase getInstance(Context context) {
        if (sInstance == null) {
            synchronized (LOCK) {
                if (sInstance == null) {
                    sInstance = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, DATABASE_NAME).fallbackToDestructiveMigration().build();
                }
            }
        }
        return sInstance;
    }

    abstract PlayerDao getPlayersDao();

}

