package com.a.anote;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.SavedStateViewModelFactory;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.text.Editable;
import android.text.Selection;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;

public class EditorActivity extends AppCompatActivity {
    private Notes notes;
    NoteRepository repository;
    NoteDataViewModel noteDataViewModel;
    SettingHandler settingHandler;
    SeekBar seekBar;
    TextView tv_count;
    EditText  et_title;
    EditText  textarea;
    TextView tv_size;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);

        noteDataViewModel = new ViewModelProvider(this, new SavedStateViewModelFactory(getApplication(), this)).get(NoteDataViewModel.class);
        repository=noteDataViewModel.getNoteRepository();
        settingHandler = new SettingHandler(this);
        notes=new Notes("0");
        Bundle bundle= getIntent().getExtras();
        notes.setUid(bundle.getString("uid"));
        notes.setContent(bundle.getString("content"));
        notes.setNid(bundle.getString("nid"));
        notes.setLastTime(bundle.getString("last_time"));
        notes.setTitle(bundle.getString("title"));

        tv_size=findViewById(R.id.fsize);
        seekBar=findViewById(R.id.sb_font);
        et_title=findViewById(R.id.editTextTextPersonName);
        textarea=findViewById(R.id.textarea);
        tv_count=findViewById(R.id.tv_count);
    }

    @Override
    protected void onResume() {
        super.onResume();
        noteDataViewModel.getNoteRepository().getAllNotes(settingHandler.getUid());
        et_title.setText(notes.getTitle());
        textarea.setText(notes.getContent());
        tv_count.setText("字数："+String.valueOf(notes.getContent().length()) );
        textarea.setTextSize(settingHandler.getFontSize());
        seekBar.setProgress(settingHandler.getFontSize());
        tv_size.setText("字体:"+seekBar.getProgress());
        Selection.setSelection(textarea.getText(),textarea.getText().length());
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tv_size.setText("字体:"+seekBar.getProgress());
                textarea.setTextSize(seekBar.getProgress());
                settingHandler.setFontSize(seekBar.getProgress());
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        textarea.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                notes.setContent(textarea.getText().toString());
                tv_count.setText("字数："+String.valueOf(notes.getContent().length()) );
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        notes.setTitle(et_title.getText().toString());
        notes.setLastTime(String.valueOf(new Date().getTime()) );
        notes.setContent(textarea.getText().toString());
        repository.updateNotes(notes);
        Toast.makeText(this,"保存成功",Toast.LENGTH_SHORT).show();
    }
}