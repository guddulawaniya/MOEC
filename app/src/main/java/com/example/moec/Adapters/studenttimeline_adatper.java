package com.example.moec.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moec.ModulesClass.studentline_module;
import com.example.moec.R;

import java.util.ArrayList;

public class studenttimeline_adatper extends RecyclerView.Adapter<studenttimeline_adatper.viewholdr> {


    ArrayList<studentline_module> list;

    public studenttimeline_adatper(ArrayList<studentline_module> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public viewholdr onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.studenttime_card,parent,false);
        return new viewholdr(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholdr holder, int position) {
        studentline_module module = list.get(position);
        holder.stepcout.setText(module.getStepcout());
        holder.stepname.setText(module.getStepname());
        holder.completdate.setText(module.getCompletdate());


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class viewholdr extends RecyclerView.ViewHolder {
        TextView stepcout, stepname,completdate;
        ImageView image;
        public viewholdr(@NonNull View itemView) {
            super(itemView);
            stepcout = itemView.findViewById(R.id.stepcount);
            stepname = itemView.findViewById(R.id.stepname);
            completdate = itemView.findViewById(R.id.completedate);
            image = itemView.findViewById(R.id.stepimag);
        }
    }


}
