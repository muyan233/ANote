package com.a.anote;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.SavedStateViewModelFactory;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegActivity extends AppCompatActivity {

    EditText et_reg_uname;
    EditText et_reg_uid;
    EditText et_reg_pwd;
    EditText et_reg_repwd;
    Button btn_reg_ok;

    NoteRepository repository;
    NoteDataViewModel noteDataViewModel;
    SettingHandler settingHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);

        noteDataViewModel = new ViewModelProvider(this, new SavedStateViewModelFactory(getApplication(), this)).get(NoteDataViewModel.class);
        repository=noteDataViewModel.getNoteRepository();
        settingHandler = new SettingHandler(this);

        et_reg_uname=findViewById(R.id.et_reg_uname);
        et_reg_uid=findViewById(R.id.et_reg_uid);
        et_reg_pwd=findViewById(R.id.et_reg_pwd);
        et_reg_repwd=findViewById(R.id.et_reg_repwd);
        btn_reg_ok=findViewById(R.id.btn_reg);

        btn_reg_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //判断输入是否符合规则，查询数据库中指定用户id是否存在，进行注册并登录
                if(et_reg_uid.getText().length()<5||et_reg_pwd.getText().length()<5||et_reg_repwd.getText().length()<5){
                    Toast.makeText(getApplicationContext(),"格式不匹配，除用户名外，其他内容长度应>=5",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(et_reg_pwd.getText().toString().equals(et_reg_repwd.getText().toString())){
                   //查询数据库中是否存在指定的uid
                    if(repository.checkUser(et_reg_uid.getText().toString())>0){
                        Toast.makeText(getApplicationContext(),"用户账号已存在",Toast.LENGTH_SHORT).show();
                        return;
                    }
                    NoteUser user=new NoteUser(et_reg_uid.getText().toString(),et_reg_pwd.getText().toString(),et_reg_uname.getText().toString());
                    repository.insertUsers(user);
                    settingHandler.setUid(et_reg_uid.getText().toString());
                    settingHandler.setUname(et_reg_uname.getText().toString());
                    Toast.makeText(getApplicationContext(),"注册成功并登录",Toast.LENGTH_SHORT).show();
                    finish();
                }else
                {
                    Toast.makeText(getApplicationContext(),"两次输入密码不匹配",Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}