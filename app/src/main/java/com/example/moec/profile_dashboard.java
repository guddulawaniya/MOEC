package com.example.moec;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;

import com.example.moec.loginActivity.login_Activity_with_mobile_no;
import com.theartofdev.edmodo.cropper.CropImage;


public class profile_dashboard extends AppCompatActivity {

    int SELECT_PICTURE =200;
    ImageView userpic;

    private static final int CAMERA_REQUEST = 100;
    private static final int STORAGE_REQUEST = 200;

    String cameraPermission[];
    String storagePermission[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_dashboard);


        TextView toolbartitle = findViewById(R.id.toolbar_title);
        TextView basic_information = findViewById(R.id.basic_information);
        TextView bank_details = findViewById(R.id.bank_details);
        TextView my_Documents = findViewById(R.id.my_Documents);
        TextView my_transations = findViewById(R.id.my_transations);

        TextView logout = findViewById(R.id.logout);
        TextView studentname = findViewById(R.id.studentname);
        TextView contactbnumber = findViewById(R.id.contactbnumber);
        TextView emailaddress = findViewById(R.id.emailaddress);
        TextView dob = findViewById(R.id.dob);

        TextView my_preference_profile = findViewById(R.id.my_preference_profile);

        TextView cleartext = findViewById(R.id.cleartext);
        toolbartitle.setText("My Profile");
        cleartext.setText("");

        SharedPreferences preferences = getSharedPreferences("registrationform",MODE_PRIVATE);
        String firstname = preferences.getString("Fname",null);
        String lastname =   preferences.getString("Lname",null);
        String emailid =  preferences.getString("Email",null);
        String contact =  preferences.getString("number",null);
        String dobget=  preferences.getString("DOb",null);
        //set data on load activity

        studentname.setText(firstname+" "+lastname);
        dob.setText(dobget);
        emailaddress.setText(emailid);
        contactbnumber.setText(contact);



        cleartext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Setting_Actvity.class));
            }
        });

        ImageView backbutton = findViewById(R.id.backbutton);
        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        cameraPermission = new String[]{android.Manifest.permission.CAMERA,android.Manifest.permission.WRITE_EXTERNAL_STORAGE};
        storagePermission = new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE};



        CardView editimage = findViewById(R.id.editimage);
        userpic  = findViewById(R.id.setimage);

        editimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageChooser();
            }
        });


        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), login_Activity_with_mobile_no.class));
                finish();
            }
        });
        bank_details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), bank_details.class));
            }
        });
        basic_information.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), basic_details_activity.class));
            }
        });
        my_Documents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), My_documents_upload.class));
            }
        });
        my_preference_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), program_preference_Activity.class));
            }
        });
        my_transations.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), my_transactions_activity.class));
            }
        });

    }






    private void requestStoragePermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(storagePermission, STORAGE_REQUEST);
        }
    }

    // Requesting camera permission
    private void requestCameraPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(cameraPermission, CAMERA_REQUEST);
        }
    }
    private Boolean checkStoragePermission() {
        boolean result = ContextCompat.checkSelfPermission(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE) == (PackageManager.PERMISSION_GRANTED);
        return result;
    }


    // checking camera permissions
    private Boolean checkCameraPermission() {
        boolean result = ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA) == (PackageManager.PERMISSION_GRANTED);
        boolean result1 = ContextCompat.checkSelfPermission(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE) == (PackageManager.PERMISSION_GRANTED);
        return result && result1;
    }

    // Requesting  gallery permission



    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case CAMERA_REQUEST: {
                if (grantResults.length > 0) {
                    boolean camera_accepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    boolean writeStorageaccepted = grantResults[1] == PackageManager.PERMISSION_GRANTED;

                    if (camera_accepted && writeStorageaccepted) {
                        pickFromGallery();
                    }
                    else {
                        Toast.makeText(this, "Please Enable Camera and Storage Permissions", Toast.LENGTH_LONG).show();
                    }
                }
            }
            break;

            case STORAGE_REQUEST: {
                if (grantResults.length > 0) {

                    boolean writeStorageaccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    if (writeStorageaccepted) {
                        pickFromGallery();
                    }
                    else {
                        Toast.makeText(this, "Please Enable Storage Permissions", Toast.LENGTH_LONG).show();
                    }
                }
            }
            break;
        }
    }


    private void pickFromGallery() {
        CropImage.activity().start(profile_dashboard.this);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {

                Uri resultUri = result.getUri();
                userpic.setImageURI(resultUri);
//                Picasso.with(this).load(resultUri).into(userpic);
            }
        }
    }



    private void showImagePicDialog() {

        String options[] = {"Camera", "Gallery"};

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Pick Image From");
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (which == 0) {
                    if (!checkCameraPermission()) {
                        requestCameraPermission();
                    }
                    else {
                        pickFromGallery();
                    }
                } else if (which == 1) {
                    if (!checkStoragePermission()) {
                        requestStoragePermission();
                    } else {
                        pickFromGallery();
                    }
                }
            }
        });
        builder.create().show();
    }



    void imageChooser() {



        Intent pickImageIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        pickImageIntent.setType("image/*");
        pickImageIntent.putExtra("crop", "true");
        pickImageIntent.putExtra("outputX", 200);
        pickImageIntent.putExtra("outputY", 200);
        pickImageIntent.putExtra("aspectX", 1);
        pickImageIntent.putExtra("aspectY", 1);
        pickImageIntent.putExtra("scale", true);
        pickImageIntent.putExtra(MediaStore.EXTRA_OUTPUT, "/downloads");
        pickImageIntent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());


        startActivityForResult(pickImageIntent, SELECT_PICTURE);

        // create an instance of the
        // intent of the type image
        Intent i = new Intent();
        i.setType("image/*");
        i.setAction(Intent.ACTION_GET_CONTENT);

        // pass the constant to compare it
        // with the returned requestCode
        startActivityForResult(Intent.createChooser(i, "Select Picture"), SELECT_PICTURE);
    }

}