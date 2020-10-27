package com.example.eggmoney

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import android.widget.Toolbar
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity

class CouponRegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coupon_register)
        setSupportActionBar(findViewById(R.id.coupon_toolbar))
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true);

//        val toolbar = findViewById(R.id.coupon_toolbar) as Toolbar

        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        when (id) {
            android.R.id.home -> {
//                Toast.makeText(this, "쿠폰 액티비티 테스트", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, InfoActivity::class.java))
                //onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

}