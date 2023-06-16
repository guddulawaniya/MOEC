package com.example.moec.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.example.moec.R;
import com.smarteist.autoimageslider.SliderViewAdapter;
public class SliderAdapter extends SliderViewAdapter<SliderAdapter.Holder> {


    int [] images;

    public SliderAdapter(int[] images) {
        this.images = images;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.sliderimage,parent,false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(Holder viewHolder, int position) {
        viewHolder.imageView.setImageResource(images[position]);

    }

    @Override
    public int getCount() {
        return images.length;
    }

    public class Holder extends  ViewHolder{

        ImageView imageView;
        Button buttonnext,buttoncircle;

        public Holder(View itemView){
            super(itemView);
            imageView = itemView.findViewById(R.id.image_view);
            buttoncircle = itemView.findViewById(R.id.nextbuttonprogressbar);
            buttonnext = itemView.findViewById(R.id.materialNestbbutton);


        }
    }

}