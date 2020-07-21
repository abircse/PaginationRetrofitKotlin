package com.example.recyclerviewpagination

import com.example.recyclerviewpagination.model.Response
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiInterface {
    @FormUrlEncoded
    @POST("fetchdata.php")
    fun fetchData(@Field("page") page: Int,
                  @Field("row_per_page") row_per_page: Int): Call<Response>
}