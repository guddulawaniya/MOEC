package com.example.moec.Adapters;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moec.ModulesClass.studentline_module;
import com.example.moec.R;

import java.util.ArrayList;

public class stepview_Adapter extends RecyclerView.Adapter<stepview_Adapter.viewholdr> {


    Context context;
    String[] list = {"1","2","3","4","5","6"};

    public stepview_Adapter(Context context) {

        this.context = context;
    }
    @NonNull
    @Override
    public viewholdr onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.stepview_card,parent,false);
        return new viewholdr(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull viewholdr holder, int position) {

        holder.imageview.setText(list[position]);



        SharedPreferences preferences = context.getSharedPreferences("registrationform", MODE_PRIVATE);
        int stepcount = preferences.getInt("timeline",0);




        if (position<stepcount)
            {
                  holder.image.setBackgroundResource(R.drawable.vertical_green_step_progressbar);
                  holder.imageview.setText("");
                  holder.imageview.setCompoundDrawablesWithIntrinsicBounds(R.drawable.done_white_color, 0, 0, 0);
                  holder.lines.setBackgroundColor(ContextCompat.getColor(context,R.color.done_step_item));
            }
            else if (position==stepcount)
            {
                holder.image.setBackgroundResource(R.drawable.vertical_yellow_step_progressbar);
//                holder.imageview.setCompoundDrawablesWithIntrinsicBounds(R.drawable.arrow_down, 0, 0, 0);
            }
            else if (position==5)
        {
            holder.lines.setVisibility(View.GONE);

        }
    }

    @Override
    public int getItemCount() {
        return list.length;
    }

    class viewholdr extends RecyclerView.ViewHolder {
        LinearLayout image;
        TextView imageview;

        View lines;

        public viewholdr(@NonNull View itemView) {
            super(itemView);

            lines = itemView.findViewById(R.id.lines);
            image = itemView.findViewById(R.id.imagecard);
            imageview = itemView.findViewById(R.id.stepimag);

        }
    }


}
