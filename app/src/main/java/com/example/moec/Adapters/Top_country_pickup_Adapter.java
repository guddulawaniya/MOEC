package com.example.moec.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moec.ModulesClass.Top_country_module;
import com.example.moec.ModulesClass.module_all_program;
import com.example.moec.R;
import com.example.moec.webviewActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Top_country_pickup_Adapter extends RecyclerView.Adapter<Top_country_pickup_Adapter.viewdholder> {


    ArrayList<module_all_program> list;
    Context context;

    public Top_country_pickup_Adapter(Context context, ArrayList<module_all_program> list) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public viewdholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.top_country_preferred_layout, parent, false);
        return new viewdholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewdholder holder, int position) {
        module_all_program module = list.get(position);

        Picasso.get()
                .load(module.getImage())
                .fit()
                .into(holder.imageView);
        holder.countryname.setText(module.getName());
        int id = position;
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context, webviewActivity.class);
                switch (id) {
                    case 0:
                        intent.putExtra("url", "https://www.merideanoverseas.in/study-in-uk");

                        intent.putExtra("titlename", "United Kingdom");
                        break;
                    case 1:
                        intent.putExtra("url", "https://www.merideanoverseas.in/study-in-canada");
                        intent.putExtra("titlename", "Canada");
                        break;
                    case 2:
                        intent.putExtra("url", "https://www.merideanoverseas.in/study-in-latvia");
                        intent.putExtra("titlename", "Latvia");
                        break;
                    case 3:
                        intent.putExtra("url", "https://www.merideanoverseas.in/study-in-new-zealand");
                        intent.putExtra("titlename", "New zealand");
                        break;
                    case 4:
                        intent.putExtra("url", "https://www.merideanoverseas.in/study-in-italy");
                        intent.putExtra("titlename", "Italy");
                        break;
                    case 5:
                        intent.putExtra("url", "https://www.merideanoverseas.in/study-in-australia");
                        intent.putExtra("titlename", "Australia");
                        break;
                    case 6:
                        intent.putExtra("url", "https://www.merideanoverseas.in/study-in-usa");
                        intent.putExtra("titlename", "USA");
                        break;
                }


                context.startActivity(intent);
            }
        });

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
