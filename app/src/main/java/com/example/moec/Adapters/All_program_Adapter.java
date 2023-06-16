package com.example.moec.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moec.ModulesClass.module_all_program;
import com.example.moec.Program_details;
import com.example.moec.R;

import java.util.ArrayList;

public class All_program_Adapter extends RecyclerView.Adapter<All_program_Adapter.viewholder> {

    ArrayList<module_all_program> list ;
    Context context;

    public All_program_Adapter(ArrayList<module_all_program> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.all_programs_card_layout,parent,false);
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
        module_all_program module = list.get(position);
        holder.coursename.setText(module.getCoursename());
        holder.duration.setText(module.getDuration());
        holder.countryname.setText(module.getCountryname());
        holder.collegename.setText(module.getCollegename());
        holder.fees.setText(module.getFees());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context, Program_details.class));
            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewholder extends RecyclerView.ViewHolder {
        TextView coursename,duration,countryname ,collegename,fees;
        public viewholder(@NonNull View itemView) {
            super(itemView);
            coursename = itemView.findViewById(R.id.coursename);
            duration = itemView.findViewById(R.id.course_duration);
            countryname = itemView.findViewById(R.id.countryname);
            collegename = itemView.findViewById(R.id.collegeAddress);
            fees = itemView.findViewById(R.id.tution_fee);

        }
    }
}

