package com.example.moec.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityOptionsCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moec.Accommodation_Activity;
import com.example.moec.GIC_registration;
import com.example.moec.Loan_details_Activity;
import com.example.moec.ModulesClass.Quick_Action_Module;
import com.example.moec.New_Application;
import com.example.moec.R;
import com.example.moec.SOP_Guidance_Activity;
import com.example.moec.test_Activity;

import java.util.ArrayList;

public class Quick_Action_Adapter extends RecyclerView.Adapter<Quick_Action_Adapter.viewdholder> {


    ArrayList<Quick_Action_Module> list;
    Context context;

    public Quick_Action_Adapter(Context context, ArrayList<Quick_Action_Module> list) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public viewdholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.quick_action_layout,parent,false);
        return new viewdholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewdholder holder, int position) {
        int id = position;
        Quick_Action_Module module = list.get(position);
        holder.imageView.setImageResource(module.getIconimage());
        holder.countryname.setText(module.getTitletext());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                switch (id)
                {
                    case 0:
                         Intent intent = new Intent(context, New_Application.class);
                        Bundle bundle = ActivityOptionsCompat.makeSceneTransitionAnimation((Activity) context,holder.itemView,"fab").toBundle();
                        context.startActivity(intent,bundle);

                    break;
                    case 1:
                        Intent intent1 = new Intent(context, test_Activity.class);
                        Bundle bundle1 = ActivityOptionsCompat.makeSceneTransitionAnimation((Activity) context,holder.itemView,"fab").toBundle();
                        context.startActivity(intent1,bundle1);
                    break;
                    case 2:
                       Intent intent2 = new Intent(context, GIC_registration.class);
                        Bundle bundle2 = ActivityOptionsCompat.makeSceneTransitionAnimation((Activity) context,holder.itemView,"fab").toBundle();
                        context.startActivity(intent2,bundle2);
                    break;
                    case 3:
                        Intent intent3 = new Intent(context, SOP_Guidance_Activity.class);
                        Bundle bundle3 = ActivityOptionsCompat.makeSceneTransitionAnimation((Activity) context,holder.itemView,"fab").toBundle();
                        context.startActivity(intent3,bundle3);
                    break;
                    case 4:
                        Intent intent4 = new Intent(context, Accommodation_Activity.class);
                        Bundle bundle4 = ActivityOptionsCompat.makeSceneTransitionAnimation((Activity) context,holder.itemView,"fab").toBundle();
                        context.startActivity(intent4,bundle4);
                    break;
                    case 5:
                        Intent intent5 = new Intent(context, Loan_details_Activity.class);
                        Bundle bundle5 = ActivityOptionsCompat.makeSceneTransitionAnimation((Activity) context,holder.itemView,"fab").toBundle();
                        context.startActivity(intent5,bundle5);
                    break;
                }

            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewdholder extends RecyclerView.ViewHolder {


        ImageView imageView;
        TextView countryname;
        CardView quickcard;
        public viewdholder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.actionIconimage);
            countryname = itemView.findViewById(R.id.quiokiconText);
            quickcard = itemView.findViewById(R.id.quickcard);

        }
    }

}
