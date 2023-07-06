package com.example.moec.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.moec.R;
import com.smarteist.autoimageslider.SliderViewAdapter;

public class SliderAdapter extends SliderViewAdapter<SliderAdapter.SliderAdapterViewHolder> {
    int [] images;

    public SliderAdapter(int[] images) {
        this.images = images;
    }

    @Override
    public SliderAdapterViewHolder onCreateViewHolder(ViewGroup parent) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sliderimage,parent,false);
        return new SliderAdapterViewHolder(view);
    }


    @Override
    public void onBindViewHolder(SliderAdapterViewHolder viewHolder, int position) {
        viewHolder.imageView.setImageResource(images[position]);

    }

    @Override
    public int getCount() {
        return images.length;
    }

    public class SliderAdapterViewHolder extends  ViewHolder{

        ImageView imageView;

        public SliderAdapterViewHolder(View itemView){
            super(itemView);
            imageView = itemView.findViewById(R.id.image_view);


        }
    }


}
