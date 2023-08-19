package com.example.moec.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SearchView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moec.JavaClass.config;
import com.example.moec.JavaClass.getCourse_All_dataa_API;
import com.example.moec.ModulesClass.module_all_program;
import com.example.moec.R;

import java.util.ArrayList;
import java.util.List;


public class All_Programs_fragment extends Fragment {
    private ListView listView;
    private ArrayAdapter<String> adapter;
    private List<String> itemList;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_all__programs_fragment, container, false);

        // finds the ids
        ProgressBar progressBar = view.findViewById(R.id.course_progressbar);
        RecyclerView recyclerView = view.findViewById(R.id.courses_recyclerview);

        SearchView programSearchview = view.findViewById(R.id.programSearchview);

        // array list instances
        ArrayList<module_all_program> list = new ArrayList<>();



        // get the data from api
        new getCourse_All_dataa_API(progressBar, list, getContext(), recyclerView, config.Base_url + "courseprogrameApiData");


        return view;
    }

}