package com.example.moec.loginActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.moec.Config;
import com.example.moec.JavaClass.InternetConnection;
import com.example.moec.MainActivity;
import com.example.moec.R;
import com.example.moec.registrationSaveData;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONObject;

public class login_Activity extends AppCompatActivity {


    AlertDialog.Builder builder;

    TextInputEditText  emailidlg,passwordlg;
    TextInputLayout emaillayoutlg,passlayoutlg;
    ProgressDialog progressBar;
    int userid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);

        emailidlg = findViewById(R.id.emailidlg);
        passwordlg = findViewById(R.id.passwordlg);
        Button loginbutton = findViewById(R.id.loginbutton);
        TextView forgetlink = findViewById(R.id.forgetpasswordlg);
        TextView registrainlink = findViewById(R.id.registraionlink);
        emaillayoutlg = findViewById(R.id.emaillayoutlg);
        passlayoutlg = findViewById(R.id.passwordlayoutlg);

        progressBar = new ProgressDialog(login_Activity.this);
        progressBar.setCancelable(true);
        progressBar.setIcon(R.drawable.logo_symbol_colour);
        progressBar.setTitle("Login");
        progressBar.setMessage("Please Wait..");


        builder = new AlertDialog.Builder(this);
        builder.setTitle("Login details");


        textwatherError();
        InternetConnection nt = new InternetConnection(getApplicationContext());



        loginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String emailtext = emailidlg.getText().toString().trim();
                String passtext = passwordlg.getText().toString().trim();

                if (!emailtext.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(emailtext).matches() && !passtext.isEmpty() && nt.isConnected()) {

                    String registrationURL = Config.Base_url+"login.php" + "?email=" + emailtext + "&password=" + passtext;
                    registrationSaveData registrationSaveData = new registrationSaveData(login_Activity.this);
                    registrationSaveData.RegistrationAPI(registrationURL,"Login User..",0);

                }

                else if(!nt.isConnected())
                {
                    Toast.makeText(login_Activity.this, "Please Check Your Internet Connection", Toast.LENGTH_SHORT).show();
                }
                else if (emailtext.isEmpty())
                {
                    emaillayoutlg.startAnimation(AnimationUtils.loadAnimation(getApplication(),R.anim.shake_text));
                    emaillayoutlg.setError("Required*");
                    emailidlg.requestFocus();
                }
                else if (passtext.isEmpty()) {
                    passlayoutlg.startAnimation(AnimationUtils.loadAnimation(getApplication(),R.anim.shake_text));
                    passlayoutlg.setError("Required*");
                    passwordlg.requestFocus();
                }
                else if (passtext.length()>=16) {
                    passlayoutlg.startAnimation(AnimationUtils.loadAnimation(getApplication(),R.anim.shake_text));

                }


            }
        });



        forgetlink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(login_Activity.this, Forget_password.class));
                overridePendingTransition(R.anim.right_in_activity, R.anim.left_out_activity);
            }
        });


        registrainlink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(login_Activity.this, registration_Activity.class));

                overridePendingTransition(R.anim.left_in,R.anim.right_out);
                finish();

            }
        });

    }

    private void errorshow(TextInputLayout layout, TextInputEditText text)
    {
        layout.startAnimation(AnimationUtils.loadAnimation(getApplication(),R.anim.shake_text));
        layout.setBoxStrokeErrorColor(ColorStateList.valueOf(Color.RED));
        layout.setErrorTextColor(ColorStateList.valueOf(Color.RED));
        layout.setError("Invalid Id and Password ");
        text.requestFocus();
    }

    void textwatherError()

    {
        emailidlg.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {


                emaillayoutlg.setErrorEnabled(false);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        passwordlg.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

             if (charSequence.length()>0 && charSequence.length()<=16)
             {
                 passlayoutlg.setErrorEnabled(false);
             }
             else if (charSequence.length()>=16)
             {
                 passlayoutlg.setError("Password Length is Long");
                 passlayoutlg.setCounterEnabled(true);
                 passlayoutlg.setCounterMaxLength(16);
                 passwordlg.requestFocus();

             }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }



 /*   void RegistrationAPI(String email, String pass) {

        progressBar.show();

        String registrationURL = Config.Base_url+"login.php" + "?email=" + email + "&password=" + pass;


        class registration extends AsyncTask<String, String, String> {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected void onPostExecute(String s) {

                try {
                    JSONObject obj = new JSONObject(s);
                    int status = obj.getInt("status");

                    if (status == 1) {
                        int userid = obj.getInt("user_id");

                        getdataAPI(String.valueOf(userid));


                    }
                    else
                    {
                        progressBar.dismiss();
                        errorshow(passlayoutlg,passwordlg);
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
        obj.execute(registrationURL);
    }
    void getdataAPI(String User_id) {


        String registrationURL = Config.Base_url+"logingetdata.php?" + "user_id="+User_id;


        class registration extends AsyncTask<String, String, String> {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected void onPostExecute(String s) {

                try {
                    JSONObject obj = new JSONObject(s);
                    int status = obj.getInt("status");


                    if (status == 1) {
                        progressBar.dismiss();
                        JSONObject userdata = obj.getJSONObject("userdata");

                        Toast.makeText(login_Activity.this, "Successfully", Toast.LENGTH_SHORT).show();

                        SharedPreferences.Editor editor = getSharedPreferences("registrationform",MODE_PRIVATE).edit();
                        editor.putString("Fname",userdata.getString("firstname"));
                        editor.putString("Lname",userdata.getString("lastname"));
                        editor.putString("number",userdata.getString("number"));
                        editor.putString("email",userdata.getString("email"));
                        editor.putString("DOb",userdata.getString("dob"));
                        editor.putString("g",userdata.getString("gender"));
                        editor.putString("pincode",userdata.getString("pincode"));
                        editor.putString("qualification",userdata.getString("country"));
                        editor.putString("qualification",userdata.getString("insterest_area"));
                        editor.putString("examname",userdata.getString("english_exam"));
                        editor.putString("write",userdata.getString("write_marks"));
                        editor.putString("read",userdata.getString("read_marks"));
                        editor.putString("listen",userdata.getString("listening_marks"));
                        editor.putString("speak",userdata.getString("speaking_marks"));
                        editor.putString("overall",userdata.getString("ovarall_marks"));
                        editor.putString("userid",obj.getString("userid"));
                        editor.commit();


                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.right_in_activity,R.anim.left_out_activity);
                        finish();

                    }
                    else
                    {
                        progressBar.dismiss();
                        errorshow(passlayoutlg,passwordlg);
                    }
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }


                super.onPostExecute(s);
                return s;
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
        obj.execute(registrationURL);
    }*/




    void logincode(String emailtext, String passwordtext)

    {
        String addurl = Config.Base_url+"?email="+emailtext+"&password="+passwordtext;


        StringRequest stringRequest = new StringRequest(Request.Method.GET, addurl, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject obj = new JSONObject(response);
                    String status = obj.getString("status");

                    if (status.equals("True"))
                    {
                        SharedPreferences.Editor editor = getSharedPreferences("login",MODE_PRIVATE).edit();
                        editor.putString("LOGIN_EMAIL",emailtext);
                        editor.putString("LOGIN_PASSWORD",passwordtext);
                        editor.commit();

                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                        finish();
                    }

                }
                catch (Exception e)
                {
                    Toast.makeText(login_Activity.this, "Error"+e.getMessage(), Toast.LENGTH_SHORT).show();
                }

            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(login_Activity.this, "Error"+error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
    }


}