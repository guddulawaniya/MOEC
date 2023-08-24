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

import androidx.annotation.NonNull;
import androidx.core.app.ActivityOptionsCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moec.ModulesClass.module_all_program;
import com.example.moec.Program_details;
import com.example.moec.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Favorate_Adapter extends RecyclerView.Adapter<Favorate_Adapter.viewholder> {

    Context context;
    ArrayList<module_all_program> list;
    boolean hidelike=false;


    public Favorate_Adapter(Context context, ArrayList<module_all_program> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.reccommended_program, parent, false);
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {

        module_all_program module = list.get(position);
        holder.coursename.setText(module.getCoursename());
        holder.duration.setText(module.getDuration());
        holder.countryname.setText(module.getCountryname());
        holder.collegename.setText(module.getCollegename());

        Picasso.get()
                .load(module.getUniversityimage())
                .resize(300, 100)
                .into(holder.universityimage);

        if (module.getDuration().equals("null"))
        {
            holder.duration.setText("");
        }else holder.duration.setText(module.getDuration());

        if (module.getFees().equals("null")) {
            holder.fees.setText("");
        } else holder.fees.setText(module.getFees() + " Months");


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = context.getSharedPreferences("programdetails", Context.MODE_PRIVATE).edit();
                editor.putString("coursename", module.getCoursename());
                editor.putString("duration", module.getDuration());
                editor.putString("countryname", module.getCountryname());
                editor.putString("collegename", module.getCollegename());
                editor.putString("imageURL", module.getUniversityimage());
                editor.putString("fees", module.getFees());
                editor.putString("intake", module.getIntake());
                editor.putString("weblink", module.getLink());
                editor.putString("criteria", module.getCriteria());
                editor.putString("courseid", module.getCourseid());
                editor.putString("favoratevalue", module.getFavoratevalue());
                editor.commit();

                Intent intent = new Intent(context, Program_details.class);
                Bundle bundle = ActivityOptionsCompat.makeSceneTransitionAnimation((Activity) context, holder.itemView, "item").toBundle();
                context.startActivity(intent, bundle);

            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewholder extends RecyclerView.ViewHolder {
        TextView coursename, duration, countryname, collegename, fees;
        ImageView favoriteiconbutton, universityimage;


        public viewholder(@NonNull View itemView) {
            super(itemView);
            coursename = itemView.findViewById(R.id.coursename);
            duration = itemView.findViewById(R.id.course_duration);
            countryname = itemView.findViewById(R.id.countryname);
            collegename = itemView.findViewById(R.id.collegeAddress);
            fees = itemView.findViewById(R.id.tution_fee);
            favoriteiconbutton = itemView.findViewById(R.id.favoriteiconbutton);
            universityimage = itemView.findViewById(R.id.universityimage);

            favoriteiconbutton.setVisibility(View.GONE);

        }
    }
}
