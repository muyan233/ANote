package com.a.anote;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.SavedStateViewModelFactory;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    EditText et_lg_uid;
    EditText et_lg_pwd;
    Button btn_lg;

    NoteRepository repository;
    NoteDataViewModel noteDataViewModel;
    SettingHandler settingHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        noteDataViewModel = new ViewModelProvider(this, new SavedStateViewModelFactory(getApplication(), this)).get(NoteDataViewModel.class);
        repository=noteDataViewModel.getNoteRepository();
        settingHandler = new SettingHandler(this);

        et_lg_uid=findViewById(R.id.et_login_uid);
        et_lg_pwd=findViewById(R.id.et_login_pwd);
        btn_lg=findViewById(R.id.btn_login);

        btn_lg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //判断输入是否符合规则，查询数据库中指定用户id是否存在，进行注册并登录
                if(et_lg_uid.getText().length()<5||et_lg_pwd.getText().length()<5 ){
                    Toast.makeText(getApplicationContext(),"账号或密码长度应>=5",Toast.LENGTH_SHORT).show();
                    return;
                }
                {
                    //查询数据库中是否存在指定的uid与pwd匹配的项
                    String uname=repository.getName(et_lg_uid.getText().toString(),et_lg_pwd.getText().toString());
                    if (uname==null) {
                        Toast.makeText(getApplicationContext(), "登录失败，账号或密码错误", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    settingHandler.setUid(et_lg_uid.getText().toString());
                   // Log.d("TAG", "onClick: "+uname);
                    settingHandler.setUname(uname);
                    Toast.makeText(getApplicationContext(), "登录成功！", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });
    }
}