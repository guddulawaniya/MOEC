package com.example.moec;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class studenttimeline_adatper extends RecyclerView.Adapter<studenttimeline_adatper.viewholdr> {


    ArrayList<studentline> list;

    public studenttimeline_adatper(ArrayList<studentline> list) {
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
        studentline module = list.get(position);
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
class studentline
{
    public studentline(String stepcout, String stepname, String completdate, int image) {
        this.stepcout = stepcout;
        this.stepname = stepname;
        this.completdate = completdate;
        this.image = image;
    }


    public String getStepcout() {
        return stepcout;
    }

    public void setStepcout(String stepcout) {
        this.stepcout = stepcout;
    }

    public String getStepname() {
        return stepname;
    }

    public void setStepname(String stepname) {
        this.stepname = stepname;
    }

    public String getCompletdate() {
        return completdate;
    }

    public void setCompletdate(String completdate) {
        this.completdate = completdate;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    String stepcout, stepname,completdate;
    int image;

}