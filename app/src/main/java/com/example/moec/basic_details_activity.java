package com.example.moec;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;


import android.Manifest;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.theartofdev.edmodo.cropper.CropImage;

import java.util.List;

public class basic_details_activity extends AppCompatActivity {

    private static final int SELECT_PICTURE = 200;
    private TextView studentname, emailaddress, dob, gender, country, state, city, number, pincode, nationality;
    private CardView editimage;
    private ImageView userpic;
    private ProgressBar imageprogressbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_details);


        studentname = findViewById(R.id.studentname);
        emailaddress = findViewById(R.id.emailaddress);
        dob = findViewById(R.id.dob);
        gender = findViewById(R.id.gender);
        country = findViewById(R.id.country);
        state = findViewById(R.id.state);
        city = findViewById(R.id.city);
        number = findViewById(R.id.mobilenumber);
        pincode = findViewById(R.id.pincode);
        nationality = findViewById(R.id.nationality);
        editimage = findViewById(R.id.editimage);
        userpic = findViewById(R.id.setimage);
        imageprogressbar = findViewById(R.id.imageprogressbaar);


        // Toolbar Expressions

        TextView title = findViewById(R.id.toolbar_title);
        title.setText("Basic Information");
        ImageView backbutton = findViewById(R.id.backbutton);
        TextView cleartext = findViewById(R.id.cleartext);
        cleartext.setVisibility(View.GONE);


        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        editimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getPermissions();
            }
        });

    }

    private void getPermissions() {

        Dexter.withActivity(this)

                .withPermissions(Manifest.permission.CAMERA)
                .withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport multiplePermissionsReport) {

                        if (multiplePermissionsReport.areAllPermissionsGranted()) {

                            imageChooser();
                        }

                        if (multiplePermissionsReport.isAnyPermissionPermanentlyDenied()) {

                            showSettingsDialog();
                        }
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> list, PermissionToken permissionToken) {

                        permissionToken.continuePermissionRequest();
                    }
                }).withErrorListener(error -> {

                    Toast.makeText(getApplicationContext(), "Error occurred! ", Toast.LENGTH_SHORT).show();
                })
                .onSameThread().check();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
            if (resultCode == RESULT_OK) {
                Uri resultUri = data.getData();
                userpic.setImageURI(resultUri);
            } else if (requestCode==200) {
                if (requestCode == SELECT_PICTURE) {

                    Uri resultUri = data.getData();
                    if (resultUri!=null) {

                        userpic.setImageURI(resultUri);
                    }
                }
        }
    }
    void imageChooser() {
        Intent i = new Intent();
        i.setType("image/*");
        i.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(i, "Select Picture"), SELECT_PICTURE);


    }

    private void showSettingsDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(basic_details_activity.this);

        builder.setTitle("Need Permissions");

        builder.setMessage("This app needs permission to use this feature. You can grant them in app settings.");
        builder.setPositiveButton("GOTO SETTINGS", (dialog, which) -> {

            dialog.cancel();

            Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
            Uri uri = Uri.fromParts("package", getPackageName(), null);
            intent.setData(uri);
            startActivityForResult(intent, 101);
        });
        builder.setNegativeButton("Cancel", (dialog, which) -> {
            dialog.cancel();
        });
        builder.show();
    }


    @Override
    protected void onStart() {
        // get the data and set data on screen On start Activity
        SharedPreferences preferences = getSharedPreferences("registrationform", MODE_PRIVATE);
        studentname.setText(preferences.getString("Fname", "") + preferences.getString("Lname", ""));
        emailaddress.setText(preferences.getString("email", ""));
        number.setText(preferences.getString("number", ""));
        dob.setText(preferences.getString("DOb", ""));
        gender.setText(preferences.getString("g", ""));
        country.setText(preferences.getString("Country", ""));
        state.setText(preferences.getString("State", ""));
        city.setText(preferences.getString("City", ""));
        pincode.setText(preferences.getString("pincode", ""));
        nationality.setText(preferences.getString("nationality", ""));



        super.onStart();
    }
}