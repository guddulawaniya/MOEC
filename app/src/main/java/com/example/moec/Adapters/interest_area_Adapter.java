package com.example.moec.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moec.ModulesClass.interest_module;
import com.example.moec.R;

import java.util.ArrayList;

public class interest_area_Adapter extends RecyclerView.Adapter<interest_area_Adapter.viewholder> {

    ArrayList<interest_module> list ;
     int lastItemSelectedPos = -1;
    int selectedItemPos = -1;

    private int checkedPosition = -1;


    public interest_area_Adapter(ArrayList<interest_module> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.interest_layout,parent,false);
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
        interest_module module = list.get(position);
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
            image = itemView.findViewById(R.id.interest_image);
            textview = itemView.findViewById(R.id.interest_title);
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

