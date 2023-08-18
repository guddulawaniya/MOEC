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
import com.example.moec.R;
import com.example.moec.webviewActivity;

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
        View view = LayoutInflater.from(context).inflate(R.layout.top_country_preferred_layout, parent, false);
        return new viewdholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewdholder holder, int position) {
        Top_country_module module = list.get(position);
        holder.imageView.setImageResource(module.getCountryimage());
        holder.countryname.setText(module.getCountryname());
        int id = position;
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context, webviewActivity.class);
                switch (id) {
                    case 0:
                        intent.putExtra("url", "https://www.meridean.org/study-in-canada");
                        intent.putExtra("titlename", "Canada");
                        break;
                    case 1:
                        intent.putExtra("url", "https://www.meridean.org/study-in-australia");
                        intent.putExtra("titlename", "Australia");
                        break;
                    case 2:
                        intent.putExtra("url", "https://www.merideanoverseas.in/study-in-uk");
                        intent.putExtra("titlename", "United Kingdom");
                        break;
                    case 3:
                        intent.putExtra("url", "https://www.meridean.org/study-in-usa");
                        intent.putExtra("titlename", "United States");
                        break;
                    case 4:
                        intent.putExtra("url", "https://www.meridean.org/germany-summer-intake");
                        intent.putExtra("titlename", "United States");
                        break;
                    case 5:
                        intent.putExtra("url", "https://www.merideanoverseas.in/cost-of-study-in-newzealand");
                        intent.putExtra("titlename", "New Zealand");
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
