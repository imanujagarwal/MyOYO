package com.example.oyotest

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Button
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.oyotest.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var vm:ColorViewModel
    lateinit var mBinding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        vm = ViewModelProvider(this, ViewModelFactory(application)).get(ColorViewModel::class.java)
        initializeClickListeners()
        initializeObservers()

    }

    private fun initializeClickListeners() {
        mBinding.tvHello.setOnClickListener {
            vm.getColor()
        }
    }

    fun observeColor() {
        vm.buttonColor.observe(this, Observer<String> {
            Handler().postDelayed(Runnable {
                mBinding.tvHello.setBackgroundColor(Color.parseColor(it))
                SharedPrefUtil.setPrefs(Constants.CURRENT_COLOR, it, this)
            }, 2000)
        })
    }

    fun initializeObservers() {
        observeColor()
        // Can observe more here:
//        observeDimensions()
//        observeShape()
    }


    class ViewModelFactory(private val context: Application) : ViewModelProvider.NewInstanceFactory() {

        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return ColorViewModel(context) as T
        }
    }

}