package com.example.moec;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.Settings;
import android.util.Base64;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.moec.Fragments.Edit_fragment;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class basic_details_activity extends AppCompatActivity {

    private static final int SELECT_PICTURE = 200;
    private TextView studentname, emailaddress, dob, gender, country, state, city, number, pincode, maritalstatus, address, nationality;
    private CardView editimage;
    private ImageView userpic;
    private ProgressBar imageprogressbar;
    String UPLOAD_IMAGE_URL = "";

    ProgressBar progressBar;
    private File selectedImageFile;


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
        maritalstatus = findViewById(R.id.maritalstatus);
        editimage = findViewById(R.id.editimage);
        userpic = findViewById(R.id.setimage);
        imageprogressbar = findViewById(R.id.imageprogressbaar);
        progressBar = findViewById(R.id.progressBar);
        address = findViewById(R.id.address);
        nationality = findViewById(R.id.nationality);

        // Toolbar Expressions
        SharedPreferences preferences = getSharedPreferences("registrationform", MODE_PRIVATE);
        String imageurl = preferences.getString("image", null);
        if (imageurl != null) {
            byte[] decodedBytes = Base64.decode(imageurl, Base64.DEFAULT);
            Bitmap decodedBitmap = BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.length);

            // Display the decodedBitmap in an ImageView
            userpic.setImageBitmap(decodedBitmap);
        }


        TextView title = findViewById(R.id.toolbar_title);
        title.setText("Basic Information");
        ImageView backbutton = findViewById(R.id.backbutton);
        TextView cleartext = findViewById(R.id.cleartext);
        cleartext.setText("Edit");
        cleartext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment dialogFragment = new Edit_fragment();
                dialogFragment.show(getSupportFragmentManager(), "fullScreenDialog");
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

        if (requestCode == SELECT_PICTURE && resultCode == RESULT_OK && data != null) {
            Uri imageUri = data.getData();
            // image_to_Base64(imageUri);
            selectedImageFile = new File(getRealPathFromURI(imageUri));
            userpic.setImageURI(imageUri);

            Bitmap bitmap = null;
            try {
                bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
                String image = encodeImage(bitmap);
                SharedPreferences.Editor editor = getSharedPreferences("registrationform", MODE_PRIVATE).edit();
                editor.putString("image", image);
                editor.commit();
                recreate();

            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            //  Toast.makeText(this, "File Path : "+selectedImageFile, Toast.LENGTH_SHORT).show();
        }

    }

    private String getRealPathFromURI(Uri contentUri) {

        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor cursor = getContentResolver().query(contentUri, proj, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String filePath = cursor.getString(column_index);
        cursor.close();
        return filePath;
    }

    private String encodeImage(Bitmap image) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        return Base64.encodeToString(imageBytes, Base64.DEFAULT);
    }

    void imageChooser() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, SELECT_PICTURE);

    }

    public void uploadImage(File imageFile) {
        OkHttpClient client = new OkHttpClient();

        // Prepare the request body
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("image", imageFile.getName(), RequestBody.create(MediaType.parse("image/*"), imageFile))
                .build();

        // Create the request
        Request request = new Request.Builder()
                .url(UPLOAD_IMAGE_URL)
                .post(requestBody)
                .build();

        // Execute the request
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Toast.makeText(basic_details_activity.this, "Failed uploading  ", Toast.LENGTH_SHORT).show();
                // Handle failure
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Toast.makeText(basic_details_activity.this, "Successfully upload image ", Toast.LENGTH_SHORT).show();
                // Handle response
            }
        });
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

    void loadfunction() {

        SharedPreferences preferences = getSharedPreferences("registrationform", MODE_PRIVATE);
        studentname.setText(preferences.getString("Fname", "") + " " + preferences.getString("Lname", ""));
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