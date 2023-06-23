package com.example.moec.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moec.R;

import java.util.ArrayList;

public class loginbeforeimage_Adapter extends RecyclerView.Adapter<loginbeforeimage_Adapter.viewholder> {



   ArrayList<slidermodule> list;
   Context context;

    public loginbeforeimage_Adapter(ArrayList<slidermodule> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.loginbeforeimage_layout,parent,false);
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {

        slidermodule slidermodule =  list.get(position);
        holder.imageView.setImageResource(slidermodule.getImages());
        holder.title.setText(slidermodule.getTitle());

        holder.itemView.onScreenStateChanged(position);


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewholder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView title;
        public viewholder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.sliderimage);
            title = itemView.findViewById(R.id.sliderTitle);

        }
    }


}