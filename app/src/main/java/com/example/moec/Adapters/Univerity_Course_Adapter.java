package com.example.moec.Adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moec.ModulesClass.Univerity_Course_Module;
import com.example.moec.R;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

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
        holder.universityname.setText(module.getUniversityname());
        Picasso.get()
                .load(module.getIconimage())
                .resize(300,100)
                .into(holder.imageView);
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
