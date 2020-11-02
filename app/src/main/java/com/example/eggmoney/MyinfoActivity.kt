package com.example.eggmoney

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.RadioGroup
import kotlinx.android.synthetic.main.activity_myinfo.*

class MyinfoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lateinit var listener1 : RadioGroup.OnCheckedChangeListener
        lateinit var listener2 : RadioGroup.OnCheckedChangeListener
        lateinit var listener3 : RadioGroup.OnCheckedChangeListener
        setContentView(R.layout.activity_myinfo)

        my_info_skip_button.setOnClickListener {
            startActivity(Intent(this, InfoActivity::class.java))
            finish()
        }
    }
}