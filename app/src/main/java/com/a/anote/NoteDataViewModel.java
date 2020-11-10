package com.a.anote;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

public class NoteDataViewModel extends AndroidViewModel {
    private final NoteRepository noteRepository;

    public NoteDataViewModel(@NonNull Application application) {
        super(application);
        noteRepository = new NoteRepository(application);
    }
    public NoteRepository getNoteRepository() {
        return noteRepository;
    }
}
