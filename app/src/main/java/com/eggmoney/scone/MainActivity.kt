package com.eggmoney.scone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler


import com.bumptech.glide.Glide

val SPLASH_VIEW_TIME : Long = 1500

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Glide.with(this)
            .load(intent.getStringExtra("profile"))
            //.into(imgProfile)


        setContentView(R.layout.activity_main)
        Handler().postDelayed({
            startActivity(Intent(this, SplashActivity::class.java))

        }, SPLASH_VIEW_TIME)



    }
}

