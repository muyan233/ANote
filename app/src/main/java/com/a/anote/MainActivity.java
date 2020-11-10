package com.a.anote;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.SavedStateViewModelFactory;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Toolbar toolbar;
    RecyclerView recyclerView;
    NoteDataViewModel noteDataViewModel;
    NoteViewAdapter noteViewAdapter;
    SettingHandler settingHandler;
    MenuItem item1, item2, item3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = findViewById(R.id.recycle);
        noteViewAdapter = new NoteViewAdapter(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(noteViewAdapter);
        settingHandler = new SettingHandler(this);
        noteDataViewModel = new ViewModelProvider(this, new SavedStateViewModelFactory(getApplication(), this)).get(NoteDataViewModel.class);

    }

    @Override
    protected void onResume() {
        super.onResume();
        if(item1!=null||item2!=null||item3!=null){
            if (settingHandler.getUid().equals("0")) {
                item2.setTitle("登录");
                item3.setTitle("注册");

            } else {
                item2.setTitle(settingHandler.getUname());
                item3.setTitle("注销");
            }
           // Log.d("TAG", "重新加载菜单");
        }
        noteDataViewModel.getNoteRepository().getAllNotes(settingHandler.getUid()).observe(this, new Observer<List<Notes>>() {
            @Override
            public void onChanged(List<Notes> notes) {
                noteViewAdapter.setNotes(notes);
                noteViewAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolabr_menu, menu);
        item1 = menu.findItem(R.id.menu_setting);
        item2 = menu.findItem(R.id.menu_user);
        item3 = menu.findItem(R.id.menu_reg);

        if (settingHandler.getUid().equals("0")) {
            item2.setTitle("登录");
            item3.setTitle("注册");

        } else {
            item2.setTitle(settingHandler.getUname());
            item3.setTitle("注销");
        }
       // Log.d("TAG", "onCreateOptionsMenu: ");
        return super.onCreateOptionsMenu(menu);
    }

    public void add(View view) {
        Notes note = new Notes(String.valueOf(new Date().getTime()));
        //NoteLocalSettings settings=noteDataViewModel.getNoteRepository().getLocalSettings().getValue().get(0);
        //note.setFontSize(settings.getFontSize());//设置字体
        note.setUid(settingHandler.getUid());//设置文本所属用户
        noteDataViewModel.getNoteRepository().insertNotes(note);

        Intent intent=new Intent(this,EditorActivity.class);
        intent.putExtra("uid",note.getUid());
        intent.putExtra("title",note.getTitle());
        intent.putExtra("content",note.getContent());
        intent.putExtra("nid",note.getNid());
        intent.putExtra("last_time",note.getLastTime());
        startActivity(intent);
    }

    public void reg(MenuItem item) {
            //注册用户界面
        if(settingHandler.getUid().equals("0")){//没有用户登录，此处为注册
            //启动注册界面
            Intent intent=new Intent(this,RegActivity.class);
            startActivity(intent);
        }
        else//有用户登录，此处为退出登录
        {
            settingHandler.setUid("0");
            settingHandler.setUname("ANote");
            onResume();
        }
    }

    public void login(MenuItem item) {
        if(!settingHandler.getUid().equals("0")){
            return;
        }
        Intent intent=new Intent(this,LoginActivity.class);
        startActivity(intent);
    }

    public void setting(MenuItem item) {
        Intent intent=new Intent(this,SettingActivity.class);
        startActivity(intent);
    }

}