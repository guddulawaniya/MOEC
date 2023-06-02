package com.example.moec;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class All_program_Adapter extends RecyclerView.Adapter<All_program_Adapter.viewholder> {

    ArrayList<module_all_program> list ;

    public All_program_Adapter(ArrayList<module_all_program> list) {
        this.list = list;
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

class module_all_program
{
  String coursename,duration,fees,countryname ,collegename;

    public module_all_program(String coursename, String duration, String fees, String countryname, String collegename) {
        this.coursename = coursename;
        this.duration = duration;
        this.fees = fees;
        this.countryname = countryname;
        this.collegename = collegename;
    }

    public String getCoursename() {
        return coursename;
    }

    public void setCoursename(String coursename) {
        this.coursename = coursename;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getFees() {
        return fees;
    }

    public void setFees(String location) {
        this.fees = location;
    }

    public String getCountryname() {
        return countryname;
    }

    public void setCountryname(String countryname) {
        this.countryname = countryname;
    }

    public String getCollegename() {
        return collegename;
    }

    public void setCollegename(String collegename) {
        this.collegename = collegename;
    }
}
