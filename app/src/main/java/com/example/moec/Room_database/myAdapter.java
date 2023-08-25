package com.example.moec.Room_database;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moec.R;

import java.util.List;

public class myAdapter extends RecyclerView.Adapter<myAdapter.viewholder> {

    List<database_module> list;
    searchfunction_call call;
    public myAdapter(List<database_module> list,searchfunction_call call) {
        this.list = list;
        this.call = call;


    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_card, parent, false);

        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
        database_module module = list.get(position);

           holder.name.setText(module.getText());


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                call.settext(module.getText());
            }
        });



    }

    @Override
    public int getItemCount() {
        return 15;
    }

    public class viewholder extends RecyclerView.ViewHolder {

        TextView name;

        public viewholder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.textview);

        }
    }
}
