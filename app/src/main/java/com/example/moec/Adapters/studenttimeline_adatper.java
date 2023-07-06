package com.example.moec.Adapters;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moec.ModulesClass.studentline_module;
import com.example.moec.R;

import java.util.ArrayList;

public class studenttimeline_adatper extends RecyclerView.Adapter<studenttimeline_adatper.viewholdr> {


    ArrayList<studentline_module> list;
    Context context;


    public studenttimeline_adatper(ArrayList<studentline_module> list,Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public viewholdr onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.studenttime_card,parent,false);
        return new viewholdr(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull viewholdr holder, int position) {
        studentline_module module = list.get(position);
        holder.stepcout.setText(module.getStepcout());
        holder.stepname.setText(module.getStepname());
        holder.completdate.setText(module.getCompletdate());

        SharedPreferences preferences = context.getSharedPreferences("registrationform", MODE_PRIVATE);
        int stepcount = preferences.getInt("timeline",0);


            if (position<stepcount)
            {
                  holder.imagecard.setBackgroundResource(R.drawable.signup_linearbg_timeline);
            }
            else if (position==stepcount)
            {
                holder.imagecard.setBackgroundResource(R.drawable.current_select_card);
            }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class viewholdr extends RecyclerView.ViewHolder {
        TextView stepcout, stepname,completdate;
        ImageView image;
        ImageView infoicon;
        LinearLayout imagecard;
        CardView universalcard;
        public viewholdr(@NonNull View itemView) {
            super(itemView);
            stepcout = itemView.findViewById(R.id.stepcount);
            stepname = itemView.findViewById(R.id.stepname);
            completdate = itemView.findViewById(R.id.completedate);
            image = itemView.findViewById(R.id.stepimag);
            imagecard = itemView.findViewById(R.id.imagecard);
            infoicon = itemView.findViewById(R.id.infoicon);
            universalcard = itemView.findViewById(R.id.universalcard);
        }
    }


}
