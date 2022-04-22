package com.example.recycleview.persondb;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {personDataClass.class},version = 1)
public abstract class PersonRoomDataBase extends RoomDatabase{
    public abstract PersonDAO personDAO();
    private static volatile PersonRoomDataBase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static PersonRoomDataBase getDatabase(final Context context){
        // thread-safe implementation, using lazy initialization with double-checked locking
        if (INSTANCE == null) { //double-checked locking
            synchronized (PersonRoomDataBase.class) {
                if (INSTANCE == null) { //double-checked locking
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), PersonRoomDataBase.class, "person").build();
                }
            }
        }
        return INSTANCE;
    }



    @NonNull
    @Override
    protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration config) {
        return null;
    }

    @NonNull
    @Override
    protected InvalidationTracker createInvalidationTracker() {
        return null;
    }

    @Override
    public void clearAllTables() {

    }
}
