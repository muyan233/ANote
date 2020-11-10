package com.a.anote;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.SavedStateViewModelFactory;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class SettingActivity extends AppCompatActivity {
    private Button btn_clear;
    private SeekBar seekBar;
    private TextView tv_font;


    NoteRepository repository;
    NoteDataViewModel noteDataViewModel;
    SettingHandler settingHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        btn_clear=findViewById(R.id.btn_clear);
        seekBar=findViewById(R.id.seekBar2);
        tv_font=findViewById(R.id.tv_size_info);

        noteDataViewModel = new ViewModelProvider(this, new SavedStateViewModelFactory(getApplication(), this)).get(NoteDataViewModel.class);
        repository=noteDataViewModel.getNoteRepository();
        settingHandler = new SettingHandler(this);


        seekBar.setProgress(settingHandler.getFontSize());
        tv_font.setText(String.valueOf(settingHandler.getFontSize()));
        btn_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                repository.clearNotes();//清除日志
                Toast.makeText(getApplicationContext(),"清除完成",Toast.LENGTH_SHORT).show();
                finish();
            }
        });
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tv_font.setText(String.valueOf(seekBar.getProgress()));
                settingHandler.setFontSize(seekBar.getProgress());
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }


}