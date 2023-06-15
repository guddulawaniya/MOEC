package com.example.moec;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class most_prefered_destination_Adapter extends RecyclerView.Adapter<most_prefered_destination_Adapter.viewholder> {


    ArrayList<most_prefered_destination_module> list;


    private int checkedPosition = -1;
    public most_prefered_destination_Adapter(ArrayList<most_prefered_destination_module> list) {
        this.list = list;
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
        public viewholder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.countryimagemost);
            textview = itemView.findViewById(R.id.countrynamemost);
            selectedcard = itemView.findViewById(R.id.selectedcard);


        }


        void bind() {

                if (checkedPosition == getAdapterPosition()) {
                    selectedcard.setVisibility(View.VISIBLE);
                } else {
                    selectedcard.setVisibility(View.GONE);
                }

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    selectedcard.setVisibility(View.VISIBLE);
                    if (checkedPosition != getAdapterPosition()) {
                        notifyItemChanged(checkedPosition);
                        checkedPosition = getAdapterPosition();
                    }
                }
            });
        }
    }
}

class most_prefered_destination_module
{
    int image;
    String text;

    public most_prefered_destination_module(int image, String text) {
        this.image = image;
        this.text = text;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

}
