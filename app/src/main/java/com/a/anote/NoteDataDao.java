package com.a.anote;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface NoteDataDao {

    @Insert
    void insertUsers(NoteUser... users);
    @Insert
    void insertNotes(Notes ...notes);
    @Update
    void updateUsers(NoteUser ...users);
    @Update
    void updateNotes(Notes ...notes);
    @Delete
    void deleteUser(NoteUser... user);
    @Delete
    void deleteNotes(Notes ...notes);

    @Query("SELECT * FROM user")
    LiveData<List<NoteUser>> getUsers();
    @Query("SELECT * FROM notes WHERE uid='0' OR uid=:uid")
    LiveData<List<Notes>> getAllNotes(String uid);

    @Query("SELECT * FROM notes WHERE uid='0' ")
    LiveData<List<Notes>> getAllNotes();

    @Query("SELECT COUNT(*) From user where uid=:uid")
    int checkUser(String uid);
    @Query("SELECT COUNT(*) From user where uid=:uid AND pwd=:pwd")
    int login(String uid,String pwd);
    @Query("SELECT uname From user where uid=:uid AND pwd=:pwd")
    String getUName(String uid,String pwd);

    @Query("DELETE FROM notes WHERE uid='0'")
    void deleteAllNotes();

}
