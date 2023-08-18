package com.example.moec;

import android.app.AlertDialog;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.DialogFragment;



import android.Manifest;

import com.example.moec.Fragments.Edit_fragment;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class basic_details_activity extends AppCompatActivity {

    private static final int SELECT_PICTURE = 200;
    private TextView studentname, emailaddress, dob, gender, country, state, city, number, pincode, maritalstatus,address,nationality;
    private CardView editimage;
    private ImageView userpic;
    private ProgressBar imageprogressbar;
    String UPLOAD_IMAGE_URL= "";

    Timer mytimer;
    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_details);

        int style = AlertDialog.THEME_HOLO_LIGHT;
        studentname = findViewById(R.id.studentname);
        emailaddress = findViewById(R.id.emailaddress);
        dob = findViewById(R.id.dob);
        gender = findViewById(R.id.gender);
        country = findViewById(R.id.country);
        state = findViewById(R.id.state);
        city = findViewById(R.id.city);
        number = findViewById(R.id.mobilenumber);
        pincode = findViewById(R.id.pincode);
        maritalstatus = findViewById(R.id.maritalstatus);
        editimage = findViewById(R.id.editimage);
        userpic = findViewById(R.id.setimage);
        imageprogressbar = findViewById(R.id.imageprogressbaar);
        progressBar = findViewById(R.id.progressBar);
        address = findViewById(R.id.address);
        nationality = findViewById(R.id.nationality);

        // Toolbar Expressions

        TextView title = findViewById(R.id.toolbar_title);
        title.setText("Basic Information");
        ImageView backbutton = findViewById(R.id.backbutton);
        TextView cleartext = findViewById(R.id.cleartext);
        cleartext.setText("Edit");
        cleartext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment dialogFragment = new Edit_fragment();
                dialogFragment.show(getSupportFragmentManager(),"fullScreenDialog");
            }
        });

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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            if (requestCode == SELECT_PICTURE ) {
                Bitmap imageBitmap = (Bitmap) data.getExtras().get("data");
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                imageBitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                byte[] imageData = baos.toByteArray();
//                upload(imageData);

                // Now you can proceed to upload the imageData to the server.
            }
        }
    }


    void imageChooser() {
        Intent i = new Intent();
        i.setType("image/*");
        i.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(i, "Select Picture"), SELECT_PICTURE);

    }

    void upload(byte[] imageData){
        OkHttpClient client = new OkHttpClient();
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("image", "image.jpg", RequestBody.create(MediaType.parse("image/jpeg"), imageData))
                .build();

        Request request = new Request.Builder()
                .url(UPLOAD_IMAGE_URL)
                .post(requestBody)
                .build();

        try {
            Response response = client.newCall(request).execute();
            // Handle the response from the server
        } catch (IOException e) {
            e.printStackTrace();
        }

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

        loadfunction();
        super.onStart();
    }
    void loadfunction()
    {

        SharedPreferences preferences = getSharedPreferences("registrationform", MODE_PRIVATE);
        studentname.setText(preferences.getString("Fname", "") +" "+ preferences.getString("Lname", ""));
        emailaddress.setText(preferences.getString("email", ""));
        number.setText(preferences.getString("number", ""));
        dob.setText(preferences.getString("DOb", ""));
        gender.setText(preferences.getString("g", ""));
        country.setText(preferences.getString("country", ""));
        state.setText(preferences.getString("state", ""));
        city.setText(preferences.getString("city", ""));
        pincode.setText(preferences.getString("pincode", ""));
        maritalstatus.setText(preferences.getString("marital", ""));
        address.setText(preferences.getString("address", ""));
        nationality.setText(preferences.getString("nationality", ""));

    }

}