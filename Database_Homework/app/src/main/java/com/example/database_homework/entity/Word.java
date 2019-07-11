package com.example.database_homework.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;

@Entity(tableName = "word")
public class Word implements Parcelable {
    public Word(){

    }
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String word;
    public String partofSpeech;
    public String definition;

    protected Word(Parcel in) {
        id = in.readInt();
        word = in.readString();
        partofSpeech = in.readString();
        definition = in.readString();
    }

    public static final Creator<Word> CREATOR = new Creator<Word>() {
        @Override
        public Word createFromParcel(Parcel in) {
            return new Word(in);
        }

        @Override
        public Word[] newArray(int size) {
            return new Word[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(word);
        dest.writeString(partofSpeech);
        dest.writeString(definition);
    }
}
