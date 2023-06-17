package com.example.moec.BottomSheets;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.moec.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;


public class close_button_bottomsheet extends BottomSheetDialogFragment {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_close_button_bottomsheet, container, false);
        TextView proceedbutton = view.findViewById(R.id.proceedbutton);
        TextView cancelbutton = view.findViewById(R.id.cancelbutton);


        proceedbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().finish();
            }
        });
        cancelbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dismiss();
            }
        });

        // Inflate the layout for this fragment
        return view;
    }
}