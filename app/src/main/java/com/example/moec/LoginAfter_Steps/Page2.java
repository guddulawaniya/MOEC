package com.example.moec.LoginAfter_Steps;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moec.Adapters.interest_area_Adapter;
import com.example.moec.ModulesClass.interest_module;
import com.example.moec.R;

import java.util.ArrayList;

public class Page2 extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_page2, container, false);

        // finding ids

        RecyclerView recyclerView = view.findViewById(R.id.interest_recyclerview);


        // instance of array list
        ArrayList<interest_module> list = new ArrayList<>();


        // object of interest Adapter
        interest_area_Adapter adapter = new interest_area_Adapter(list);




       // set layout manager on recyclerview
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),3));

        recyclerView.setAdapter(adapter);

        // add data on list

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




        return view;
    }
}