package com.example.moec;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class list_adapter extends RecyclerView.Adapter<list_adapter.viewholder> {


ArrayList<listmodule> list;

    public list_adapter(ArrayList<listmodule> list, Context context) {
        this.list = list;
        this.context = context;
    }

    Context context;



    @NonNull
    @Override
    public list_adapter.viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.list_show_layout,parent,false);
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull list_adapter.viewholder holder, int position) {
        listmodule listmodule = list.get(position);

        holder.textView.setText(listmodule.getText());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                holder.textView.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.baseline_done_icon_24, 0);


            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

   public class viewholder extends RecyclerView.ViewHolder {
        TextView textView;
       public viewholder(@NonNull View itemView) {
           super(itemView);
           textView = itemView.findViewById(R.id.listtext);
       }
   }
}

class listmodule{

    String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public listmodule(String text) {
        this.text = text;
    }
}
