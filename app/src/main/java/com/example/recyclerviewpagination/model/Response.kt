package com.example.recyclerviewpagination.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Response {
    @SerializedName("message")
    @Expose
    var message: String? = null

    @SerializedName("status")
    @Expose
    var status: Int? = null

    @SerializedName("data")
    @Expose
    var data: List<Data>? = null

}