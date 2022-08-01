package com.example.tz_nord_klan.presentation.LogIn

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tz_nord_klan.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }


}