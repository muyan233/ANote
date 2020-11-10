package com.a.anote;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;

import androidx.annotation.NonNull;

public class SettingHandler {
    SharedPreferences setting;
    SharedPreferences.Editor editor;
    Context context;
    public SettingHandler(@NonNull Context context) {
        this.context=context;
        setting = context.getSharedPreferences(
                 "settings", Context.MODE_PRIVATE);
        editor=setting.edit();

    }

    public  void setFontSize(int fontSize) {
        editor.putInt("fontSize",fontSize);
        editor.commit();
    }
    public void setUid(String uid){
        editor.putString("cuid",uid);
        editor.commit();
    }
    public void setUname(String uname){
        editor.putString("cuname",uname);
        editor.commit();
    }

    public int getFontSize(){
        return setting.getInt("fontSize",14);
    }

    public String getUid(){
        return setting.getString("cuid","0");
    }

    public String getUname(){
        return setting.getString("cuname","ANote");
    }
}
