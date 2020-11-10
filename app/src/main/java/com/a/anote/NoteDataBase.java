package com.a.anote;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Notes.class, NoteUser.class},version = 1,exportSchema = false)
public abstract  class NoteDataBase extends RoomDatabase {

    private static NoteDataBase INSTANCE;

    static synchronized NoteDataBase getNoteDataBase(Context context){
        if (INSTANCE==null)
        {
            INSTANCE= Room.databaseBuilder(context.getApplicationContext(),NoteDataBase.class,"note_database").allowMainThreadQueries().build();
        }
        return INSTANCE;
    }
    public abstract NoteDataDao getNoteDao();
}
