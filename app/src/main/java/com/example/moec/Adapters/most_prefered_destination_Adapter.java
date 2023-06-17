package com.example.moec.Adapters;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moec.ModulesClass.most_prefered_destination_module;
import com.example.moec.R;

import java.util.ArrayList;

public class most_prefered_destination_Adapter extends RecyclerView.Adapter<most_prefered_destination_Adapter.viewholder> {


    ArrayList<most_prefered_destination_module> list;
    Context context;



    private int checkedPosition = -1;
    public most_prefered_destination_Adapter(ArrayList<most_prefered_destination_module> list,Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public most_prefered_destination_Adapter.viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.most_prefered_country_layout,parent,false);
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
        most_prefered_destination_module module = list.get(position);
        holder.image.setImageResource(module.getImage());
        holder.textview.setText(module.getText());
        holder.bind();

    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewholder extends RecyclerView.ViewHolder {



        ImageView image;
        TextView textview;
        CardView selectedcard;
        LinearLayout ringlinear;
        public viewholder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.countryimagemost);
            textview = itemView.findViewById(R.id.countrynamemost);
            selectedcard = itemView.findViewById(R.id.selectedcard);
            ringlinear = itemView.findViewById(R.id.ringlinear);



        }


        void bind() {

                if (checkedPosition == getAdapterPosition()) {

                    selectedcard.setVisibility(View.VISIBLE);
                    ringlinear.setVisibility(View.VISIBLE);
                } else {
                    selectedcard.setVisibility(View.INVISIBLE);
                    ringlinear.setVisibility(View.INVISIBLE);
                    SharedPreferences.Editor editor = context.getSharedPreferences("selectcountry",Context.MODE_PRIVATE).edit();
                    editor.putInt("position",checkedPosition);
                    editor.commit();
                }

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    selectedcard.setVisibility(View.VISIBLE);
                    ringlinear.setVisibility(View.VISIBLE);
                    if (checkedPosition != getAdapterPosition()) {

                        notifyItemChanged(checkedPosition);
                        checkedPosition = getAdapterPosition();

                    }
                }
            });
        }
    }
}

