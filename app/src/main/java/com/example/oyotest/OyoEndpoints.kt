package com.example.oyotest

import com.google.gson.JsonObject
import org.json.JSONObject
import retrofit2.Call
import retrofit2.http.GET

interface OyoEndpoints {

    @GET("/oyocolor")
    fun getColor(): Call<JsonObject>

}