package com.example.oyotest

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.gson.JsonObject
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.concurrent.Callable

class ColoRepo(private val application: Application) {

    var colorlive = MutableLiveData<String>()

    fun getColorFromCache() : String? {
        return SharedPrefUtil.getPrefs(Constants.CURRENT_COLOR, "#ffffff", application)
    }

    fun hitApiToGetColor(): LiveData<String>{

        val request = ServiceBuilder.buildService(OyoEndpoints::class.java)
        val call = request.getColor()

        call.enqueue(object : Callback<JsonObject> {
            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
            }

            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                if (response.body()!!.has("color")) {
                    colorlive.value = response.body()!!["color"].asString
                }
            }

        })

        colorlive.value = getColorFromCache()

        return colorlive
    }
}