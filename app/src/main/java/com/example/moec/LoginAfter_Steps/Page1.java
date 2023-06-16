package com.example.moec.LoginAfter_Steps;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moec.Adapters.most_prefered_destination_Adapter;
import com.example.moec.ModulesClass.countryList_module;
import com.example.moec.ModulesClass.most_prefered_destination_module;
import com.example.moec.R;

import java.util.ArrayList;


public class Page1 extends Fragment {


    ArrayList<countryList_module> list;
    ArrayList<most_prefered_destination_module> mostpreferedlist;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_page1, container, false);




        // finding recycler view id

        RecyclerView mostpreferedRecyclerview =  view.findViewById(R.id.mostpreferedRecyclerview);


        // instances array list

        mostpreferedlist = new ArrayList<>();
        list = new ArrayList<>();




        // set layout manager on recyclerview

        mostpreferedRecyclerview.setLayoutManager(new GridLayoutManager(getContext(),3));


        // nested scolling set false
        mostpreferedRecyclerview.setNestedScrollingEnabled(false);



        // object Adapters
        most_prefered_destination_Adapter mostAdapter = new most_prefered_destination_Adapter(mostpreferedlist);


        // set adapter on recycler view

        mostpreferedRecyclerview.setAdapter(mostAdapter);


        // add data on list
        mostpreferedlist.add(new most_prefered_destination_module(R.drawable.uk_flag,"United Kingdom"));
        mostpreferedlist.add(new most_prefered_destination_module(R.drawable.us_flag,"United State"));
        mostpreferedlist.add(new most_prefered_destination_module(R.drawable.flag_canada,"Canada"));
        mostpreferedlist.add(new most_prefered_destination_module(R.drawable.australia_flag,"Australia"));
        mostpreferedlist.add(new most_prefered_destination_module(R.drawable.flag_canada,"Italy"));
        mostpreferedlist.add(new most_prefered_destination_module(R.drawable.germany_flag,"Germany"));
        mostpreferedlist.add(new most_prefered_destination_module(R.drawable.zealand_flag,"new Zealand"));
        mostpreferedlist.add(new most_prefered_destination_module(R.drawable.dubai_flag,"Dubai"));
        mostpreferedlist.add(new most_prefered_destination_module(R.drawable.poland_flag,"Poland"));
        mostpreferedlist.add(new most_prefered_destination_module(R.drawable.ireland_flag,"Ireland"));
        mostpreferedlist.add(new most_prefered_destination_module(R.drawable.latvia_flag,"Latvia"));
        mostpreferedlist.add(new most_prefered_destination_module(R.drawable.mauritius_flg,"Mauritius"));
        mostpreferedlist.add(new most_prefered_destination_module(R.drawable.malta_flag,"Malta"));








        return view;
    }


}