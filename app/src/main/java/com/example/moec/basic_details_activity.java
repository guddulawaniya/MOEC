package com.example.moec;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.moec.loginActivity.login_Activity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class basic_details_activity extends AppCompatActivity {


    private RequestQueue mRequestQueue;
     private  TextView country,state,city;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_details);



        ImageView backbutton = findViewById(R.id.backbutton);
        TextView cleartext = findViewById(R.id.cleartext);
        TextView title = findViewById(R.id.toolbar_title);
        title.setText("Basic Information");
        cleartext.setVisibility(View.GONE);

        TextView studentname = findViewById(R.id.studentname);
        TextView emailaddress = findViewById(R.id.emailaddress);
        TextView dob = findViewById(R.id.dob);
        TextView gender = findViewById(R.id.gender);
         country = findViewById(R.id.country);
         state = findViewById(R.id.state);
         city = findViewById(R.id.city);
        TextView number = findViewById(R.id.mobilenumber);
        SharedPreferences preferences = getSharedPreferences("registrationform",MODE_PRIVATE);
        String pincode =  preferences.getString("pincode",null);

        if (pincode!=null)
        {

            fetchAddressdata(pincode);

        }




        String firstname = preferences.getString("Fname",null);
        String lastname =   preferences.getString("Lname",null);
        String emailid =  preferences.getString("email",null);
        String contact =  preferences.getString("number",null);
        String dobget=  preferences.getString("DOb",null);
        String countryget=  preferences.getString("Country",null);
        String stateget=  preferences.getString("State",null);
        String Cityget=  preferences.getString("City",null);
        String genderget=  preferences.getString("g",null);




        studentname.setText(firstname+" "+lastname);
        emailaddress.setText(emailid);
        dob.setText(dobget);
        gender.setText(genderget);
        country.setText(countryget);
        state.setText(stateget);
        city.setText(Cityget);
        number.setText(contact);

        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

    }

    void fetchAddressdata(String pincode) {

        String url = "https://api.postalpincode.in/pincode/"+pincode;


        class registration extends AsyncTask<String, String, String> {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected void onPostExecute(String s) {

                try {
                    JSONArray array = new JSONArray(s);
                    JSONObject obj = array.getJSONObject(0);
                    String status = obj.getString("Status");


                    if (status.equals("Success")) {

                        JSONArray postdata = obj.getJSONArray("PostOffice");
                        JSONObject dataobject = postdata.getJSONObject(1);

                        String district = dataobject.getString("District");
                        String States = dataobject.getString("State");
                        String Countries = dataobject.getString("Country");

                        country.setText(Countries);
                        state.setText(States);
                        city.setText(district);
                        Toast.makeText(basic_details_activity.this, "District : "+district, Toast.LENGTH_SHORT).show();

                        SharedPreferences.Editor editor = getSharedPreferences("registrationform",MODE_PRIVATE).edit();
                        editor.putString("Country",Countries);
                        editor.putString("State",States);
                        editor.putString("City",district);
                        editor.commit();


                    }
                    else
                    {
                        Toast.makeText(basic_details_activity.this, "Invalid Pincode", Toast.LENGTH_SHORT).show();
                    }
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
        registration obj = new registration();
        obj.execute(url);
    }

}