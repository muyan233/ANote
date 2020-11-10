package com.a.anote;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class NoteViewAdapter extends RecyclerView.Adapter<NoteViewAdapter.NoteViewHolder>{
    List<Notes> notes=new ArrayList<>();
    Context context;
    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View view=layoutInflater.inflate(R.layout.cycle_item,parent,false);
        return new NoteViewHolder(view);
    }

    public void setNotes(List<Notes> notes) {
        this.notes = notes;
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {

        holder.notes=notes.get(position);
        holder.tv_title.setText(holder.notes.getTitle());



         holder.del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //删除
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        NoteRepository.getDao().deleteNotes(holder.notes);
                        Log.d("TAG", "run: send delete");
                    }
                }).start();
            }
        });

        holder.constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //点击事件,编辑当前的内容
                Intent intent=new Intent(context,EditorActivity.class);
                intent.putExtra("uid",holder.notes.getUid());
                intent.putExtra("title",holder.notes.getTitle());
                intent.putExtra("content",holder.notes.getContent());
                intent.putExtra("nid",holder.notes.getNid());
                intent.putExtra("last_time",holder.notes.getLastTime());
                context.startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {
        return notes.size();
    }

    public NoteViewAdapter(Context context) {
        this.context=context;
    }

    static public class NoteViewHolder extends RecyclerView.ViewHolder{
        TextView tv_title;
        public Notes notes;
        ConstraintLayout constraintLayout;
        ImageButton del;
        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_title=itemView.findViewById(R.id.note_item);
            constraintLayout=itemView.findViewById(R.id.cardvv);
            del=itemView.findViewById(R.id.imageButton);
        }
    }
}
