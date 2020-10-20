package com.example.eggmoney

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import com.example.eggmoney.SplashActivity
val SPLASH_VIEW_TIME : Long = 1500

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContentView(R.layout.activity_main)
        Handler().postDelayed({
            startActivity(Intent(this, SplashActivity::class.java))

        }, SPLASH_VIEW_TIME)



    }
}

