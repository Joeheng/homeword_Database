package com.example.database_homework.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.database_homework.entity.Word;

import java.util.List;

@Dao
public interface WordDao {
    @Insert
    Long add(Word words);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Long[] add(List<Word> words);
    @Update
    void edit(Word word);
    @Delete
    void remove(Word word);

    @Query("SELECT * FROM word order by id asc")
    List<Word> getWordList();
}
