package com.example.moec.loginActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.AsyncTask;
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

import com.example.moec.JavaClass.InternetConnection;
import com.example.moec.JavaClass.config;
import com.example.moec.MainActivity;
import com.example.moec.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import kotlin.text.Charsets;

public class login_Activity extends AppCompatActivity {


    AlertDialog.Builder builder;
    ProgressDialog progressBar;
    TextInputEditText emailidlg, passwordlg;
    TextInputLayout emaillayoutlg, passlayoutlg;

    TextView registrainlink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);

        emailidlg = findViewById(R.id.emailidlg);
        passwordlg = findViewById(R.id.passwordlg);
        Button loginbutton = findViewById(R.id.loginbutton);
        TextView forgetlink = findViewById(R.id.forgetpasswordlg);
        registrainlink = findViewById(R.id.registraionlink);
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
                    logindata(emailtext, passtext);


                } else if (!nt.isConnected()) {
                    Toast.makeText(login_Activity.this, "Please Check Your Internet Connection", Toast.LENGTH_SHORT).show();
                } else if (emailtext.isEmpty()) {
                    emaillayoutlg.startAnimation(AnimationUtils.loadAnimation(getApplication(), R.anim.shake_text));
                    emaillayoutlg.setError("Required*");
                    emailidlg.requestFocus();
                } else if (passtext.isEmpty()) {
                    passlayoutlg.startAnimation(AnimationUtils.loadAnimation(getApplication(), R.anim.shake_text));
                    passlayoutlg.setError("Required*");
                    passwordlg.requestFocus();
                } else if (passtext.length() >= 16) {
                    passlayoutlg.startAnimation(AnimationUtils.loadAnimation(getApplication(), R.anim.shake_text));

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
                overridePendingTransition(R.anim.left_in, R.anim.right_out);

            }
        });

    }

    private void errorshow(TextInputLayout layout, TextInputEditText text) {
        layout.startAnimation(AnimationUtils.loadAnimation(getApplication(), R.anim.shake_text));
        layout.setBoxStrokeErrorColor(ColorStateList.valueOf(Color.RED));
        layout.setErrorTextColor(ColorStateList.valueOf(Color.RED));
        layout.setError("Invalid Id and Password ");
        text.requestFocus();
    }

    void textwatherError() {
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

                if (charSequence.length() > 0 && charSequence.length() <= 16) {
                    passlayoutlg.setErrorEnabled(false);
                } else if (charSequence.length() >= 16) {
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


    void logindata(String emailtext, String passtext) {
        String pass = null;
        try {
            pass = URLEncoder.encode(passtext, Charsets.UTF_8.name());
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        progressBar.show();
        String registrationURL = config.Base_url + "loginApi_data?" + "username=" + emailtext + "&password=" + pass;


        class registration extends AsyncTask<String, String, String> {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected void onPostExecute(String s) {

                try {
                    JSONObject obj = new JSONObject(s);
                    String status = obj.getString("success");

                    if (status.equals("true")) {
                        progressBar.dismiss();

                        JSONObject userdata = obj.getJSONObject("data");

                        SharedPreferences.Editor editor = getSharedPreferences("registrationform", MODE_PRIVATE).edit();

                        String first = userdata.getString("first_name");
                        String last = userdata.getString("last_name");

                        editor.putString("Fname", first);
                        editor.putString("Lname", last);
                        editor.putString("email", userdata.getString("email"));
                        editor.putString("number", userdata.getString("number"));
                        editor.putString("DOb", userdata.getString("dob"));
                        editor.putString("nationality", userdata.getString("nationality"));
                        editor.putString("g", userdata.getString("gender"));
                        editor.putString("userid", userdata.getString("id"));
                        editor.commit();

                        Toast.makeText(login_Activity.this, "Welcome back " + first + last, Toast.LENGTH_SHORT).show();
                        editor.commit();
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.right_in_activity, R.anim.left_out_activity);
                        finish();
                    } else {
                        Toast.makeText(login_Activity.this, "failed" + obj, Toast.LENGTH_SHORT).show();
                        progressBar.dismiss();
                        errorshow(passlayoutlg, passwordlg);
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


}