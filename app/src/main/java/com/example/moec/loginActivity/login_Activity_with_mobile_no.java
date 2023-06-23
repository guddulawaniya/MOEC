package com.example.moec.loginActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.moec.JavaClass.InternetConnection;
import com.example.moec.R;
import com.hbb20.CountryCodePicker;

import org.json.JSONObject;

public class login_Activity_with_mobile_no extends AppCompatActivity {
    EditText mobilenumbertext;

    CountryCodePicker codePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        codePicker=findViewById(R.id.country_code);
        Button sendotpbutton = findViewById(R.id.sendotpbutton);
        mobilenumbertext = findViewById(R.id.mobilenumbertext);

        InternetConnection nt = new InternetConnection(this);

        sendotpbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!(mobilenumbertext.getText().toString().isEmpty()) && nt.isConnected())
                {
//                    String sendotp= new DecimalFormat("0000").format(new Random().nextInt(9999));
                    String sendotp = "1234";
                    final String message = "Hello ! The One Time Password to login for Staff panel is " + sendotp + " This OTP will expire in 10 minutes Regards, Meridean Overseas Edu Con Pvt Ltd";
                    String sendotpurl = "https://api.datagenit.com/sms?auth=D!~7113Zz8MHFw1mQ&senderid=MOECOE&msisdn=" + mobilenumbertext.getText().toString() + "&message=" + message;

//                    sendotpnumbers sm = new sendotpnumbers(getApplicationContext());
//                    sm.execute(sendotpurl);

                    SharedPreferences.Editor editor = getSharedPreferences("registrationform",MODE_PRIVATE).edit();
                    editor.putString("number",mobilenumbertext.getText().toString());

                    Intent intent = new Intent(getApplicationContext(), verify_OTP_Activity.class);
                    intent.putExtra("otp",sendotp);
                    intent.putExtra("id",1);
                    intent.putExtra("number",mobilenumbertext.getText().toString());
                    startActivity(intent);
                    finish();

                }
                else
                {
                    mobilenumbertext.setError("Please Enter Mobile number");
                    mobilenumbertext.requestFocus();
                }
            }
        });







    }


//    @Override
//    protected void onStart() {
//
//        if (mobilenumbertext!=null)
//        {
//            startActivity(new Intent(login_Activity.this, MainActivity.class));
//            Toast.makeText(this, "Welcome Back ", Toast.LENGTH_LONG).show();
//            finish();
//        }
//        super.onStart();
//    }




    void logincode(String  number)

    {
        String url = "";
        String addurl = url+"?mobilenumber="+number;

        StringRequest stringRequest = new StringRequest(Request.Method.GET, addurl, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject obj = new JSONObject(response);
                    String status = obj.getString("status");

                    if (status.equals("True"))
                    {
                        SharedPreferences.Editor editor = getSharedPreferences("savenumber",MODE_PRIVATE).edit();
                        editor.putString("number",number);
                        editor.commit();

                        Intent intent = new Intent(getApplicationContext(), verify_OTP_Activity.class);
                        startActivity(intent);
                        finish();
                    }



                }
                catch (Exception e)
                {
                    Toast.makeText(login_Activity_with_mobile_no.this, "Error"+e.getMessage(), Toast.LENGTH_SHORT).show();
                }

            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(login_Activity_with_mobile_no.this, "Error"+error.getMessage(), Toast.LENGTH_SHORT).show();

            }


        });

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
    }
}