package com.example.moec.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moec.R;

public class shimmer_program_Adapter extends RecyclerView.Adapter<shimmer_program_Adapter.viewholder> {


    @NonNull
    @Override
    public shimmer_program_Adapter.viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.program_shimmer_effect_layout, parent, false);
        return new shimmer_program_Adapter.viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {

    }


    @Override
    public int getItemCount() {
        return 10;
    }

    public class viewholder extends RecyclerView.ViewHolder {


        public viewholder(@NonNull View itemView) {
            super(itemView);


        }
    }
}
