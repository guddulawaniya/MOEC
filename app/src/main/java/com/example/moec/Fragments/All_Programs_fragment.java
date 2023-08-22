package com.example.moec.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
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
    ProgressBar progressBar;
    ArrayList<module_all_program> list;
    RecyclerView recyclerView;
    LinearLayout nofoundlayout;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_all__programs_fragment, container, false);

        // finds the ids
        progressBar = view.findViewById(R.id.course_progressbar);
        recyclerView = view.findViewById(R.id.courses_recyclerview);
        nofoundlayout = view.findViewById(R.id.nofoundlayout);

        SearchView programSearchview = view.findViewById(R.id.programSearchview);

             list = new ArrayList<>();

        // get the data from api
        new getCourse_All_dataa_API(progressBar, list, getContext(), recyclerView, config.Base_url + "courseprogrameApiData",nofoundlayout);

        programSearchview.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String text) {
                if (text.length()>2)
                {
                    list.clear();
                    filter(text);
                }
                else {
                    list.clear();

                    new getCourse_All_dataa_API(progressBar, list, getContext(), recyclerView, config.Base_url + "courseprogrameApiData",nofoundlayout);
                }

                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (newText.length()>2)
                {
                    list.clear();
                    filter(newText);
                } else {

                    list.clear();
                    new getCourse_All_dataa_API(progressBar, list, getContext(), recyclerView, config.Base_url + "courseprogrameApiData",nofoundlayout);
                }
                return true;
            }
        });

        return view;
    }
    private void filter(String query) {
        new getCourse_All_dataa_API(progressBar, list, getContext(), recyclerView, config.Base_url + "searchcourseprogrameApiData?search="+query,nofoundlayout);

    }

}