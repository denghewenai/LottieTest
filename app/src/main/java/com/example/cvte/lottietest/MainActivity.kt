package com.example.cvte.lottietest

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        simple_button.setOnClickListener { v -> startActivity(Intent(v.context,SimpleActivity::class.java)) }
        control_button.setOnClickListener { v -> startActivity(Intent(v.context,ControlActivity::class.java)) }
        loading_button.setOnClickListener { v -> startActivity(Intent(v.context,LoadingActivity::class.java)) }
    }
}
