package com.example.moec;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class countryList_Adapter extends RecyclerView.Adapter<countryList_Adapter.viewholder> {


ArrayList<listmodule> list;
    Context context;
    private int checkedPosition = -1;


    public countryList_Adapter(ArrayList<listmodule> list, Context context) {
        this.list = list;
        this.context = context;
    }





    @NonNull
    @Override
    public countryList_Adapter.viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.countrylist_layout,parent,false);
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull countryList_Adapter.viewholder holder, int position) {
        listmodule listmodule = list.get(position);
        holder.textView.setText(listmodule.getText());
        holder.bind();

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


       void bind() {
//
                   if (checkedPosition == getAdapterPosition())
                   {
                       textView.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.baseline_done_icon_24, 0);
                   } else
                   {
                       textView.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, 0, 0);
                   }
           itemView.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   textView.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.baseline_done_icon_24, 0);
                   if (checkedPosition != getAdapterPosition()) {
                       notifyItemChanged(checkedPosition);
                       checkedPosition = getAdapterPosition();
                   }
               }
           });
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
