package com.example.eggmoney

import android.annotation.SuppressLint
import android.content.Intent
import android.database.Cursor
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_search_result.*



class SearchResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_result)

        stock_name.text = intent.getStringExtra("stock_name")

        activity_search_gift_button.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK);
            intent.setData(Uri.parse("content://com.android.contacts/data/phones"));
            startActivityForResult(intent, 10);
            
//            var id:String = Uri.parse(data.getDataString()).getLastPathSegment();


            // TODO: 전화번호부 가져오기
            // TODO: 서버에게 stock_name 혹은 종목코드로 선물 요청 보내기
            // TODO: 난수 코드 받기
            // TODO: 전화번호 받으면 그걸로 SMS로 난수코드 보내기
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

    }
}


