package com.example.oyotest

import android.content.Context
import android.content.SharedPreferences

object SharedPrefUtil {

    fun getSharedPref(context: Context):SharedPreferences{
        return context.getSharedPreferences("hi.oyo", Context.MODE_PRIVATE)
    }

    fun setPrefs(key:String, value:String, context: Context){
        getSharedPref(context).edit().putString(key, value)
    }

    fun getPrefs(key:String, defaultValue:String, context: Context): String? {
        return getSharedPref(context).getString(key, defaultValue)
    }
}
