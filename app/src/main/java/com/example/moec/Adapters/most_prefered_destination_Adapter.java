package com.example.moec.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moec.ModulesClass.module_all_program;
import com.example.moec.R;
import com.example.moec.onClickInterface;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class most_prefered_destination_Adapter extends RecyclerView.Adapter<most_prefered_destination_Adapter.viewholder> {


    ArrayList<module_all_program> list;

    Context context;
     onClickInterface onclickInterface;
    int checkedPosition = -1;

    public most_prefered_destination_Adapter(ArrayList<module_all_program> list, Context context, onClickInterface onclickInterface) {
        this.list = list;
        this.context = context;
        this.onclickInterface = onclickInterface;
    }


    @NonNull
    @Override
    public most_prefered_destination_Adapter.viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.most_prefered_country_layout,parent,false);
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
        module_all_program module = list.get(position);
        Picasso.get()
                .load(module.getImage())
                .into(holder.image);
        holder.textview.setText(module.getName());
        holder.bind();

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                holder.selectedcard.setVisibility(View.VISIBLE);
                holder.ringlinear.setVisibility(View.VISIBLE);

                    int position=holder.getAdapterPosition();

                     String text = module.getName();
                    onclickInterface.setClick(position, text);
                    checkedPosition = position;

                if (checkedPosition != holder.getAdapterPosition()) {
                    notifyItemChanged(checkedPosition);
                }
                notifyDataSetChanged();

            }
        });

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

                }
        }
    }
}

