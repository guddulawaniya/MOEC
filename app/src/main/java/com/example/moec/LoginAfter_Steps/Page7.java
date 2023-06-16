package com.example.moec.LoginAfter_Steps;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.chaos.view.PinView;
import com.example.moec.MainActivity;
import com.example.moec.R;

public class Page7 extends Fragment {


PinView pinView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_page7, container, false);
        // Inflate the layout for this fragment

        pinView=view.findViewById(R.id.pinview);

        CheckBox passcheck = view.findViewById(R.id.passcheck);

        Button nextbutton = view.findViewById(R.id.nextbutton);
        nextbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               startActivity(new Intent(getContext(), MainActivity.class));
               getActivity().finish();

            }
        });
        
        return view;
    }


}