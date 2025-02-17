package com.example.moec.Adapters;

import android.os.Build;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moec.ModulesClass.all_feeds_module;
import com.example.moec.R;

import java.util.ArrayList;

public class all_feed_Adapter extends RecyclerView.Adapter<all_feed_Adapter.viewholdr> {


    ArrayList<all_feeds_module> list;

    public all_feed_Adapter(ArrayList<all_feeds_module> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public viewholdr onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.all_feed_card_layout,parent,false);
        return new viewholdr(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholdr holder, int position) {
        all_feeds_module module = list.get(position);
        holder.clientname.setText(module.getClientname());
        holder.descripation.setText(module.getDescripation());

        holder.menubutton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {
                PopupMenu popupMenu = new PopupMenu(view.getContext(),  holder.menubutton);
                popupMenu.setGravity(Gravity.RIGHT);

                // Inflating popup menu from popup_menu.xml file
                popupMenu.getMenuInflater().inflate(R.menu.camment_menu, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {

                        if (menuItem.getItemId()==R.id.chat)
                        {
                            Toast.makeText(view.getContext(), " Chat are Clicked " + menuItem.getTitle(), Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            Toast.makeText(view.getContext(), "Delete are  Clicked " + menuItem.getTitle(), Toast.LENGTH_SHORT).show();
                        }


                        return true;
                    }
                });
                popupMenu.show();
            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class viewholdr extends RecyclerView.ViewHolder {
        TextView clientname, descripation,time,comments,helpful;
        ImageView image,menubutton,like_icon,message_icon;
        public viewholdr(@NonNull View itemView) {
            super(itemView);
            clientname = itemView.findViewById(R.id.clienname);
            descripation = itemView.findViewById(R.id.descripation);
            time = itemView.findViewById(R.id.time);
            comments = itemView.findViewById(R.id.comments);
            helpful = itemView.findViewById(R.id.helpful);
            like_icon = itemView.findViewById(R.id.like);
            menubutton = itemView.findViewById(R.id.menubutton);
            message_icon = itemView.findViewById(R.id.message_icon);
            image = itemView.findViewById(R.id.clientimage);

        }
    }
}
