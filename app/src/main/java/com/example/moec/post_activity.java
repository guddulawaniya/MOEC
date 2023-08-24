package com.example.moec;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.moec.JavaClass.config;
import com.example.moec.ModulesClass.module_all_program;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.android.material.transition.platform.MaterialContainerTransform;
import com.google.android.material.transition.platform.MaterialContainerTransformSharedElementCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class post_activity extends AppCompatActivity {

    AutoCompleteTextView topicselection,countryselection;
    ArrayList<String> countrynamelist ;
    String[] topicList = {"Select Topic", "course ",};
    String registrationURL= config.Base_url+"crmcountriesApiData";
    TextInputEditText messagebox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        config();
        setContentView(R.layout.activity_post);
        countrynamelist = new ArrayList<>();


        TextView title = findViewById(R.id.toolbar_title);
        TextView cleartext = findViewById(R.id.cleartext);
         messagebox = findViewById(R.id.messagebox);
        ImageView canclebutton = findViewById(R.id.backbutton);
        Button submitbutton = findViewById(R.id.submitbutton);
         topicselection = findViewById(R.id.topicselection);
        countryselection = findViewById(R.id.countryselection);
        cleartext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              topicselection.setText("");
                countryselection.setText("");
                messagebox.setText("");
            }
        });

        canclebutton.setImageResource(R.drawable.close_icon);
        ArrayAdapter<module_all_program> topicadapter = new ArrayAdapter(post_activity.this,  R.layout.countrylist_layout,topicList );
        topicselection.setThreshold(100);
        topicselection.setAdapter(topicadapter);
        topicselection.setTextColor(Color.BLACK);
        topicselection.setDropDownBackgroundResource(R.color.background_blue_shadew);
        SharedPreferences preferences = getSharedPreferences("registrationform",MODE_PRIVATE);
        String userid = preferences.getString("userid",null);



        submitbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String topicTEXT = topicselection.getText().toString();
                String countriTEXT = countryselection.getText().toString();
                String sms = messagebox.getText().toString();
                if (!topicTEXT.isEmpty() && !countriTEXT.isEmpty() && !sms.isEmpty()){
                    String insertdataURL= config.Base_url+"insertAppQueryDataApi"+"?userid="+userid+"&topic="+topicTEXT+"&country="+countriTEXT+"&message="+sms;
                    insertdata data = new insertdata();
                    data.execute(insertdataURL);
                }


            }
        });

        canclebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        registration obj = new registration();
        obj.execute(registrationURL);

        title.setText("Add Post");
    }
    private void config() {
        findViewById(android.R.id.content).setTransitionName("fab");

        setEnterSharedElementCallback(new MaterialContainerTransformSharedElementCallback());
        MaterialContainerTransform transform = new MaterialContainerTransform();
        transform.addTarget(android.R.id.content);
        transform.setDuration(500);

        getWindow().setSharedElementEnterTransition(transform);
        getWindow().setSharedElementReturnTransition(transform);

    }
    class registration extends AsyncTask<String, String, String> {
        @Override
        protected void onPostExecute(String s) {

            try {
                JSONObject obj = new JSONObject(s);
                String status = obj.getString("success");

                if (status.equals("true")) {

                    JSONArray array = obj.getJSONArray("data");

                    for (int i = 0; i < array.length(); ++i) {

                        JSONObject jsonObject = array.getJSONObject(i);

                        String countryname = jsonObject.getString("country");
                      //  String topic = jsonObject.getString("topic");

                        countrynamelist.add(countryname);

                    }
                } else {
                    Toast.makeText(post_activity.this, "failed", Toast.LENGTH_SHORT).show();
                }

                ArrayAdapter<module_all_program> adapter = new ArrayAdapter(post_activity.this,  R.layout.countrylist_layout,countrynamelist );
                countryselection.setThreshold(100);
                countryselection.setAdapter(adapter);
                countryselection.setTextColor(Color.BLACK);
                countryselection.setDropDownBackgroundResource(R.color.background_blue_shadew);




            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
            super.onPostExecute(s);
        }

        @Override
        protected String doInBackground(String... param) {

            try {
                URL url = new URL(param[0]);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                return br.readLine();
            } catch (Exception ex) {
                return ex.getMessage();
            }
        }

    }


    class insertdata extends AsyncTask<String, String, String> {
        @Override
        protected void onPostExecute(String s) {

            try {
                JSONObject obj = new JSONObject(s);
                String status = obj.getString("success");

                if (status.equals("true")) {

                    Toast.makeText(post_activity.this, "Your query has been submitted with us . we'll contact you soon ", Toast.LENGTH_SHORT).show();

                    topicselection.setText("");
                    countryselection.setText("");
                    messagebox.setText("");
                    messagebox.clearFocus();

                } else {
                    Toast.makeText(post_activity.this, "failed", Toast.LENGTH_SHORT).show();
                }

                ArrayAdapter<module_all_program> adapter = new ArrayAdapter(post_activity.this,  R.layout.countrylist_layout,countrynamelist );
                countryselection.setThreshold(100);
                countryselection.setAdapter(adapter);
                countryselection.setTextColor(Color.BLACK);
                countryselection.setDropDownBackgroundResource(R.color.background_blue_shadew);




            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
            super.onPostExecute(s);
        }

        @Override
        protected String doInBackground(String... param) {

            try {
                URL url = new URL(param[0]);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                return br.readLine();
            } catch (Exception ex) {
                return ex.getMessage();
            }
        }

    }
}