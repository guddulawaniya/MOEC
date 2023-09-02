package com.example.moec.Fragments;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moec.Adapters.All_program_Adapter;
import com.example.moec.Adapters.shimmer_program_Adapter;
import com.example.moec.JavaClass.config;
import com.example.moec.JavaClass.getCourse_All_dataa_API;
import com.example.moec.ModulesClass.module_all_program;
import com.example.moec.R;
import com.facebook.shimmer.ShimmerFrameLayout;

import java.util.ArrayList;
import java.util.List;


public class All_Programs_fragment extends Fragment {
    ArrayList<module_all_program> list;
    RecyclerView recyclerView,simmer_layout_recyclerview;

    int count = 0;
    private boolean isLoading = false;
    private boolean isLastPage = false;

    LinearLayout nofoundlayout;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_all__programs_fragment, container, false);

        // finds the ids
        recyclerView = view.findViewById(R.id.courses_recyclerview);
        nofoundlayout = view.findViewById(R.id.nofoundlayout);
        simmer_layout_recyclerview = view.findViewById(R.id.simmer_layout);

        shimmer_program_Adapter shimmereffect_adpater = new shimmer_program_Adapter();
        simmer_layout_recyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        simmer_layout_recyclerview.setAdapter(shimmereffect_adpater);

        SearchView programSearchview = view.findViewById(R.id.programSearchview);

             list = new ArrayList<>();

        // get the data from api
        new getCourse_All_dataa_API(simmer_layout_recyclerview, list, getContext(), recyclerView, config.Base_url + "courseprogrameApiData",nofoundlayout);


        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                int visibleItemCount = layoutManager.getChildCount();
                int totalItemCount = layoutManager.getItemCount();
                int firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition();

                if (!isLoading && !isLastPage) {
                    if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount
                            && firstVisibleItemPosition >= 0) {
                        // get the data from api
                        new getCourse_All_dataa_API(simmer_layout_recyclerview, list, getContext(), recyclerView, config.Base_url + "courseprogrameApiData",nofoundlayout);
                    }
                }
            }
        });
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

                    new getCourse_All_dataa_API(simmer_layout_recyclerview, list, getContext(), recyclerView, config.Base_url + "courseprogrameApiData",nofoundlayout);
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
                    new getCourse_All_dataa_API(simmer_layout_recyclerview, list, getContext(), recyclerView, config.Base_url + "courseprogrameApiData",nofoundlayout);
                }
                return true;
            }
        });

        return view;
    }

    private void filter(String query) {
        new getCourse_All_dataa_API(simmer_layout_recyclerview, list, getContext(), recyclerView, config.Base_url + "searchcourseprogrameApiData?search="+query,nofoundlayout);

    }

}