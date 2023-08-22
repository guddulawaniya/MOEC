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

import com.example.moec.ModulesClass.module_all_program;
import com.example.moec.R;
import com.example.moec.onClickInterface;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class interest_area_Adapter extends RecyclerView.Adapter<interest_area_Adapter.viewholder> {

    ArrayList<module_all_program> list ;
    private int checkedPosition = -1;
    onClickInterface onclickInterface;

    public interest_area_Adapter(ArrayList<module_all_program> list, onClickInterface onclickInterface) {
        this.list = list;
        this.onclickInterface = onclickInterface;
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.interest_layout,parent,false);
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
        module_all_program module = list.get(position);
        holder.textview.setText(module.getName());
        Picasso.get()
                .load(module.getImage())
                .fit()
                .into(holder.image);
        holder.bind();


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.selectedcard.setVisibility(View.VISIBLE);
                holder.ringlinear.setVisibility(View.VISIBLE);

                int position=holder.getAdapterPosition();
                String text = module.getName();

                onclickInterface.setClick(position,text);
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
        CardView itemcard;
        LinearLayout ringlinear;

        public viewholder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.interest_image);
            textview = itemView.findViewById(R.id.interest_title);
            selectedcard = itemView.findViewById(R.id.selectedcard);
            itemcard = itemView.findViewById(R.id.itemcard);
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

