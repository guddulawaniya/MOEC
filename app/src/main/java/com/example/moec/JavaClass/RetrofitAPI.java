package com.example.moec.JavaClass;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RetrofitAPI {
    @POST("loginApi_data")
    Call<DataModal> createPost(@Body DataModal dataModal);
}
