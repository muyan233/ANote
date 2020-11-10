package com.a.anote;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;

import java.util.List;

public class NoteRepository {
    private LiveData<List<Notes>> notes;
    private LiveData<List<NoteUser>> users;

    final private NoteDataDao noteDataDao;
    static NoteDataDao dao;

    public NoteRepository(Context context) {
        NoteDataBase noteDataBase = NoteDataBase.getNoteDataBase(context);
        noteDataDao = noteDataBase.getNoteDao();
        users = noteDataDao.getUsers();
        notes = noteDataDao.getAllNotes();
        dao = noteDataDao;
    }

    public static NoteDataDao getDao() {
        return dao;
    }

    public LiveData<List<NoteUser>> getUsers() {
        return users;
    }

    public LiveData<List<Notes>> getNotes() {
        return notes;
    }

    void insertUsers(NoteUser... users) {
        noteDataDao.insertUsers(users);
    }

    void insertNotes(Notes... notes) {
        noteDataDao.insertNotes(notes);

    }

    void updateUsers(NoteUser... users) {
        noteDataDao.updateUsers(users);
    }

    void updateNotes(Notes... notes) {
        noteDataDao.updateNotes(notes);

    }

    void deleteUsers(NoteUser users) {
        noteDataDao.deleteUser(users);

    }

    int checkUser(String uid) {
        int i = 0;
        i = noteDataDao.checkUser(uid);
        return i;
    }

    int login(String uid, String pwd) {
        int i = 0;
        i  = noteDataDao.login(uid, pwd);

        return i ;
    }

    String getName(String uid, String pwd) {
        String str = "0";
        str  = noteDataDao.getUName(uid, pwd);

        return str ;
    }


    void deleteNotes(Notes... notes) {
        noteDataDao.deleteNotes(notes);

    }

    void clearNotes() {
        noteDataDao.deleteAllNotes();

    }

    void getAllNotes() {
        notes = noteDataDao.getAllNotes();
    }

    public LiveData<List<Notes>> getAllNotes(String uid) {
        notes = noteDataDao.getAllNotes(uid);
        return notes;
    }

}
