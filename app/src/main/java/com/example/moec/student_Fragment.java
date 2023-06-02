package com.example.moec;

import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.net.URISyntaxException;
import java.util.ArrayList;

public class student_Fragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View view= inflater.inflate(R.layout.fragment_student_, container, false);

     CardView setpreference = view.findViewById(R.id.setpreference_student);
     CardView refer_friends = view.findViewById(R.id.refer_friends);
        ImageView phoneimage = view.findViewById(R.id.phoneimage);

        refer_friends.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sharingIntent = new Intent(Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, "Text");
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject");
                startActivity(Intent.createChooser(sharingIntent, "Choose Apps"));
            }
        });
        phoneimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent intent = Intent.getIntent(Intent.ACTION_DIAL);
                    intent.putExtra("7037282643",0);
                    startActivity(intent);
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        TextView viewall  = view.findViewById(R.id.view_all);
        viewall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), Recommended_fragment.class));
            }
        });

     setpreference.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
       startActivity(new Intent(getContext(), program_preference_Activity.class));
      }
     });
        ArrayList<interest_module> list = new ArrayList<>();
        RecyclerView recyclerView = view.findViewById(R.id.interest_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));

        list.add(new interest_module(R.drawable.architecture,"Architecture"));
        list.add(new interest_module(R.drawable.computer,"Computer Science"));
        list.add(new interest_module(R.drawable.graphic_design,"Design"));
        list.add(new interest_module(R.drawable.engineering,"Engineering"));
        list.add(new interest_module(R.drawable.business,"Business"));
        list.add(new interest_module(R.drawable.hospitality,"Hospitality & Tourism"));
        list.add(new interest_module(R.drawable.humanities,"Humanities & Social Science"));
        list.add(new interest_module(R.drawable.law,"Law"));
        list.add(new interest_module(R.drawable.management,"Management"));
        list.add(new interest_module(R.drawable.marketing,"Marketing & Advertising"));
        list.add(new interest_module(R.drawable.news,"Media & Journalism"));
        list.add(new interest_module(R.drawable.medical_symbol,"Medical"));
        list.add(new interest_module(R.drawable.creative_thinking,"Performing and Creative Arts"));
        list.add(new interest_module(R.drawable.science,"Science"));
        list.add(new interest_module(R.drawable.sports,"Sport & Nutrition"));
        list.add(new interest_module(R.drawable.translation,"Languages"));
        list.add(new interest_module(R.drawable.education,"Education"));
        interest_area_Adapter adapter = new interest_area_Adapter(list);
        recyclerView.setAdapter(adapter);

        return view ;
    }
}