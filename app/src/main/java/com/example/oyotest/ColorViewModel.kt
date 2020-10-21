package com.example.oyotest

import android.app.Application
import androidx.lifecycle.*

class ColorViewModel(application: Application) : AndroidViewModel(application) {

    private val repo = ColoRepo(application)

    val buttonColor: LiveData<String> =  repo.hitApiToGetColor()


    fun getColor(): LiveData<String> {
        return buttonColor
    }

}