package com.example.moec.BottomSheets;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.moec.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class upload_document_bottom_sheet extends BottomSheetDialogFragment {

    private static final int pic_id = 123;
    int SELECT_PICTURE =200;

    String[] permissionsStr = {android.Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE};
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =   inflater.inflate(R.layout.fragment_upload_document_bottom_sheet, container, false);

        LinearLayout opencamera  = view.findViewById(R.id.opencamera);
        LinearLayout opengallery = view.findViewById(R.id.opengallery);

        opencamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent camera_intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                // Start the activity with camera_intent, and request pic id
                startActivityForResult(camera_intent, pic_id);

            }
        });
        opengallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);

                startActivityForResult(Intent.createChooser(intent,"Select Picture"), SELECT_PICTURE);
            }
        });

        // Inflate the layout for this fragment
        return view;



    }




    Bitmap bitmap=null;
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {

        if (resultCode == Activity.RESULT_OK)
        {
            if (requestCode == SELECT_PICTURE)
            {
                Toast.makeText(getContext(), "Image upload Succesully ", Toast.LENGTH_SHORT).show();
            }
        }

    }
}