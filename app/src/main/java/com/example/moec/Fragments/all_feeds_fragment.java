package com.example.moec.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.moec.Adapters.all_feed_Adapter;
import com.example.moec.ModulesClass.all_feeds_module;
import com.example.moec.R;

import java.util.ArrayList;


public class all_feeds_fragment extends Fragment {

    public all_feeds_fragment() {
    }

    ArrayList<all_feeds_module> list;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
      View view=  inflater.inflate(R.layout.fragment_all_feeds_fragment, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.feeds_Recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        list = new ArrayList();

        list.add(new all_feeds_module("Arvindera Singh","anyone going to canada on 22 aug from air France flight."));
        list.add(new all_feeds_module("Arvindera Singh","anyone going to canada on 22 aug from air France flight."));
        list.add(new all_feeds_module("Arvindera Singh","anyone going to canada on 22 aug from air France flight."));
        list.add(new all_feeds_module("Arvindera Singh","anyone going to canada on 22 aug from air France flight."));
        list.add(new all_feeds_module("Arvindera Singh","anyone going to canada on 22 aug from air France flight."));
        list.add(new all_feeds_module("Arvindera Singh","anyone going to canada on 22 aug from air France flight."));
        list.add(new all_feeds_module("Arvindera Singh","anyone going to canada on 22 aug from air France flight."));
        list.add(new all_feeds_module("Arvindera Singh","anyone going to canada on 22 aug from air France flight."));
        list.add(new all_feeds_module("Arvindera Singh","anyone going to canada on 22 aug from air France flight."));
        list.add(new all_feeds_module("Arvindera Singh","anyone going to canada on 22 aug from air France flight."));
        list.add(new all_feeds_module("Arvindera Singh","anyone going to canada on 22 aug from air France flight."));
        list.add(new all_feeds_module("Arvindera Singh","anyone going to canada on 22 aug from air France flight."));

        all_feed_Adapter adapter = new all_feed_Adapter(list);

        recyclerView.setAdapter(adapter);
        return view;
    }
}