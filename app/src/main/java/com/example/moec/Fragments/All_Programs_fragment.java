package com.example.moec.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moec.JavaClass.config;
import com.example.moec.JavaClass.getuniversitydataAPI;
import com.example.moec.ModulesClass.module_all_program;
import com.example.moec.R;

import java.util.ArrayList;


public class All_Programs_fragment extends Fragment {


    boolean isLastPage= true;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_all__programs_fragment, container, false);

        // finds the ids
        ProgressBar progressBar = view.findViewById(R.id.course_progressbar);
        RecyclerView recyclerView = view.findViewById(R.id.courses_recyclerview);


        // array list instances
        ArrayList<module_all_program> list = new ArrayList<>();

        // get the data from api
        new getuniversitydataAPI(progressBar, list, getContext(), recyclerView, config.Base_url + "courseprogrameApiData", false);

        return view;
    }

}