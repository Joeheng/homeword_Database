package com.example.database_homework;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.database_homework.dao.WordDao;
import com.example.database_homework.entity.Word;

@Database(version = 1, entities = {Word.class})
public abstract class DictionaryDatabase extends RoomDatabase {
    public static final String DB_NAME = "dictionary_db";

    public abstract WordDao wordDao();

    public static DictionaryDatabase getInstance(Context context){
        return Room.databaseBuilder(context,DictionaryDatabase.class,DB_NAME).allowMainThreadQueries().build();
    }

}
