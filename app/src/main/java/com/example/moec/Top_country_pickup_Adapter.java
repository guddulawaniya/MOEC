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

public class Top_country_pickup_Adapter extends RecyclerView.Adapter<Top_country_pickup_Adapter.viewdholder> {


    ArrayList<Top_country_module> list;
    Context context;

    public Top_country_pickup_Adapter(Context context, ArrayList<Top_country_module> list) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public viewdholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.top_country_preferred_layout,parent,false);
        return new viewdholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewdholder holder, int position) {
        Top_country_module module = list.get(position);
        holder.imageView.setImageResource(module.getCountryimage());
        holder.countryname.setText(module.getCountryname());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewdholder extends RecyclerView.ViewHolder {


        ImageView imageView;
        TextView countryname;
        public viewdholder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.countryimages);
            countryname = itemView.findViewById(R.id.countryname);
        }
    }


}
class Top_country_module{
    int countryimage;
    String countryname;

    public Top_country_module(int countryimage, String countryname) {
        this.countryimage = countryimage;
        this.countryname = countryname;
    }

    public int getCountryimage() {
        return countryimage;
    }

    public void setCountryimage(int countryimage) {
        this.countryimage = countryimage;
    }

    public String getCountryname() {
        return countryname;
    }

    public void setCountryname(String countryname) {
        this.countryname = countryname;
    }
}
