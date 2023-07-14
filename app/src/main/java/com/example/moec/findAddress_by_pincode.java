package com.example.moec;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.AsyncTask;
import android.view.animation.AnimationUtils;

import com.example.moec.loginActivity.registration_Activity;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class findAddress_by_pincode {

    Context context;
    TextInputEditText text;
    TextInputLayout layout;

    public findAddress_by_pincode(Context context ) {
        this.context = context;
    }

    public void getvaribales(TextInputLayout pincodelayout, TextInputEditText pincode)
    {
         text = pincode;
         layout = pincodelayout;
    }

    public void validatepincode(TextInputLayout layout, TextInputEditText text)
    {
        layout.startAnimation(AnimationUtils.loadAnimation(context,R.anim.shake_text));
        layout.setBoxStrokeErrorColor(ColorStateList.valueOf(Color.RED));
        layout.setErrorTextColor(ColorStateList.valueOf(Color.RED));
        layout.setError("Invalid Pincode");
        text.requestFocus();
    }
  void rightpin(String district, String state,String country)
    {
        layout.setHelperTextColor(ColorStateList.valueOf(Color.GRAY));
        layout.setHelperText("District : "+district+", State : "+state + ", Country : "+country);
    }

    public void fetchAddressdata(String pincode) {

        String url = "https://api.postalpincode.in/pincode/"+pincode;


        class registration extends AsyncTask<String, String, String> {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected void onPostExecute(String s) {
                SharedPreferences.Editor editors = context.getSharedPreferences("validate",MODE_PRIVATE).edit();

                try {
                    JSONArray array = new JSONArray(s);
                    JSONObject obj = array.getJSONObject(0);
                    String status = obj.getString("Status");
                    SharedPreferences.Editor editor = context.getSharedPreferences("registrationform",MODE_PRIVATE).edit();

                    if (status.equals("Success")) {

                        JSONArray postdata = obj.getJSONArray("PostOffice");
                        JSONObject dataobject = postdata.getJSONObject(1);

                        String district = dataobject.getString("District");
                        String States = dataobject.getString("State");
                        String Countries = dataobject.getString("Country");


                        editors.putBoolean("check",true);
                        editors.commit();


                        editor.putString("Country",Countries);
                        editor.putString("State",States);
                        editor.putString("City",district);
                        editor.commit();
                        rightpin(district,States,Countries);




                    }
                    else
                    {
                        editors.putBoolean("check",false);
                        validatepincode(layout,text);
                        editor.commit();
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
