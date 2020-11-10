package com.a.anote;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;

//本地设置表
@Entity(tableName = "user",primaryKeys = {"uid"})
public class NoteUser {

    @NonNull
    @ColumnInfo(name = "uid")
    private String uid;//当前登录用户id

    @NonNull
    @ColumnInfo(name = "pwd")
    private String pwd;//当前用户密码

    @ColumnInfo(name = "uname")
    private String uname;//用户名

    public NoteUser(@NonNull String uid, @NonNull String pwd, String uname) {
        this.uid = uid;
        this.pwd = pwd;
        this.uname = uname;
    }

    @NonNull
    public String getUid() {
        return uid;
    }

    public void setUid(@NonNull String uid) {
        this.uid = uid;
    }

    @NonNull
    public String getPwd() {
        return pwd;
    }

    public void setPwd(@NonNull String pwd) {
        this.pwd = pwd;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }
}
