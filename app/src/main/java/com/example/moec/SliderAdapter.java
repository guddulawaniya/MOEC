package com.example.moec;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.smarteist.autoimageslider.SliderViewAdapter;
public class SliderAdapter extends SliderViewAdapter<SliderAdapter.Holder> {

    int[] images = {R.drawable.image1,R.drawable.image2,R.drawable.image3,R.drawable.image4};


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