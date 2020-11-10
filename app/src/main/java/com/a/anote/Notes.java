package com.a.anote;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;

//存储日记的表
@Entity(tableName = "notes", primaryKeys = {"nid", "uid"})
public class Notes {

    @NonNull
    @ColumnInfo(name = "title")
    private String title;//笔记标题

    @NonNull
    @ColumnInfo(name = "content")
    private String content;//笔记内容

    @NonNull
    @ColumnInfo(name = "nid")
    private String nid;//笔记id，由创建时间生成

    @NonNull
    @ColumnInfo(name = "lasttime")
    private String lastTime;//最后修改时间

    @NonNull
    @ColumnInfo(name = "uid")//
    private String uid;//所属用户

    public Notes(String nid ) {
        this.title ="未命名";
        this.content = "";
        this.nid = nid;
        this.lastTime = nid;
        this.uid = "0";
    }

    @NonNull
    public String getTitle() {
        return title;
    }

    public void setTitle(@NonNull String title) {
        this.title = title;
    }

    @NonNull
    public String getContent() {
        return content;
    }

    public void setContent(@NonNull String content) {
        this.content = content;
    }

    @NonNull
    public String getNid() {
        return nid;
    }

    public void setNid(@NonNull String nid) {
        this.nid = nid;
    }

    @NonNull
    public String getLastTime() {
        return lastTime;
    }

    public void setLastTime(@NonNull String lastTime) {
        this.lastTime = lastTime;
    }

    @NonNull
    public String getUid() {
        return uid;
    }

    public void setUid(@NonNull String uid) {
        this.uid = uid;
    }
}
