package com.example.moec.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityOptionsCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moec.ModulesClass.module_all_program;
import com.example.moec.New_Application;
import com.example.moec.Program_details;
import com.example.moec.R;

import java.util.ArrayList;

public class All_program_Adapter extends RecyclerView.Adapter<All_program_Adapter.viewholder> {

    ArrayList<module_all_program> list ;
    Context context;
    int id;
    boolean like = true;

    public All_program_Adapter(ArrayList<module_all_program> list, Context context,int id) {
        this.list = list;
        this.id = id;
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

                Intent intent = new Intent(context, Program_details.class);
                Bundle bundle = ActivityOptionsCompat.makeSceneTransitionAnimation((Activity) context,holder.itemView,"item").toBundle();
                context.startActivity(intent,bundle);
                
            }
        });

        if (id==1)
        {

            holder.favoriteiconbutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String coursename = module.getCoursename();
                    String duration = module.getDuration();
                    String countryname = module.getCountryname();
                    String collegename = module.getCollegename();
                    String fees = module.getFees();

                   String[] list1 = {countryname,duration,countryname,collegename,fees};
                    SharedPreferences.Editor editor = context.getSharedPreferences("favoriteProgram", Context.MODE_PRIVATE).edit();

                    if (like)
                    {
                        like=false;
                        holder.favoriteiconbutton.setImageResource(R.drawable.favorite_heart);

                    }
                    else
                    {
                        like=true;
                        holder.favoriteiconbutton.setImageResource(R.drawable.favorite_icon);
                    }



                    editor.putString("coursename",coursename);
                    editor.putString("duration",duration);
                    editor.putString("countryname",countryname);
                    editor.putString("fees",fees);
                    editor.putString("collegename",collegename);
                    editor.commit();

                }
            });

        }
        else
        {
            holder.favoriteiconbutton.setVisibility(View.GONE);

        }
    }
    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewholder extends RecyclerView.ViewHolder {
        TextView coursename,duration,countryname ,collegename,fees;
        ImageView favoriteiconbutton,universityimage;
        public viewholder(@NonNull View itemView) {
            super(itemView);
            coursename = itemView.findViewById(R.id.coursename);
            duration = itemView.findViewById(R.id.course_duration);
            countryname = itemView.findViewById(R.id.countryname);
            collegename = itemView.findViewById(R.id.collegeAddress);
            fees = itemView.findViewById(R.id.tution_fee);
            favoriteiconbutton = itemView.findViewById(R.id.favoriteiconbutton);
            universityimage = itemView.findViewById(R.id.universityimage);

        }
    }
}

