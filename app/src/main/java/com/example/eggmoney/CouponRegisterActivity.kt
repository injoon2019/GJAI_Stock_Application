package com.example.eggmoney

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import android.widget.Toolbar
import androidx.appcompat.app.AlertDialog
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.content_coupon_register.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CouponRegisterActivity : AppCompatActivity() {

    private lateinit var couponCode:String
    private val BaseURL:String = "https://scone-294002.uc.r.appspot.com"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coupon_register)
        setSupportActionBar(findViewById(R.id.coupon_toolbar))
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true);

//        val toolbar = findViewById(R.id.coupon_toolbar) as Toolbar

        coupon_register_button.setOnClickListener{
            couponCode = editTextCouponNumber.text.toString()
            val uid = intent.getStringExtra("uid")
            Toast.makeText(this, couponCode, Toast.LENGTH_SHORT).show()
            Toast.makeText(this, uid, Toast.LENGTH_SHORT).show()


            val retrofit = Retrofit.Builder().baseUrl(BaseURL) .addConverterFactory(
                GsonConverterFactory.create()).build();
            var registercoupon = retrofit.create(RegisterCoupon::class.java);

            registercoupon.registerCoupon(uid, couponCode).enqueue(object:
                Callback<CouponRegisterResponse> {
                var registercoupon_response:CouponRegisterResponse? = null
                override fun onFailure(call: Call<CouponRegisterResponse>, t: Throwable) {
//                    TODO("Not yet implemented")
                }

                override fun onResponse(
                    call: Call<CouponRegisterResponse>,
                    response: Response<CouponRegisterResponse>
                ) {
                    registercoupon_response = response.body()
                    var dialog = AlertDialog.Builder(this@CouponRegisterActivity)
                    dialog.setTitle(registercoupon_response?.ResultMessage.toString())
//                    Toast.makeText(this, coupon_code+" 쿠폰 발급", Toast.LENGTH_SHORT).show()
//                dialog.setMessage(login?.code)
                    dialog.show()
                }

            })
            Toast.makeText(this, "쿠폰등록이 완료되었습니다", Toast.LENGTH_SHORT).show()
        }



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