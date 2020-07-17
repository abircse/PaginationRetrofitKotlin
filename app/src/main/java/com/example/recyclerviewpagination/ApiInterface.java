package com.example.recyclerviewpagination;

import com.example.recyclerviewpagination.model.Response;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiInterface {

    @FormUrlEncoded
    @POST("fetchdata.php")
    Call<Response> fetchData(@Field("page") int page,
                             @Field("row_per_page") int row_per_page);
}
