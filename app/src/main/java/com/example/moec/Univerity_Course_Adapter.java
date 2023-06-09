package com.example.moec;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Univerity_Course_Adapter extends RecyclerView.Adapter<Univerity_Course_Adapter.viewdholder> {


    ArrayList<Univerity_Course_Module> list;
    Context context;

    public Univerity_Course_Adapter(Context context, ArrayList<Univerity_Course_Module> list) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public viewdholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.universities_layout,parent,false);
        return new viewdholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewdholder holder, int position) {
        Univerity_Course_Module module = list.get(position);
        holder.imageView.setImageResource(module.getIconimage());
        holder.universityname.setText(module.getUniversityname());
        holder.totoalcourse.setText(module.getTotalcourse());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewdholder extends RecyclerView.ViewHolder {


        ImageView imageView;
        TextView universityname , totoalcourse;
        public viewdholder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.universityimage);
            universityname = itemView.findViewById(R.id.univeristyname);
            totoalcourse = itemView.findViewById(R.id.universitycourses);
        }
    }


}
class Univerity_Course_Module{
    int iconimage;
    String universityname,totalcourse;


    public Univerity_Course_Module(int iconimage, String universityname, String totalcourse) {
        this.iconimage = iconimage;
        this.universityname = universityname;
        this.totalcourse = totalcourse;
    }

    public int getIconimage() {
        return iconimage;
    }

    public void setIconimage(int iconimage) {
        this.iconimage = iconimage;
    }

    public String getUniversityname() {
        return universityname;
    }

    public void setUniversityname(String universityname) {
        this.universityname = universityname;
    }

    public String getTotalcourse() {
        return totalcourse;
    }

    public void setTotalcourse(String totalcourse) {
        this.totalcourse = totalcourse;
    }
}
