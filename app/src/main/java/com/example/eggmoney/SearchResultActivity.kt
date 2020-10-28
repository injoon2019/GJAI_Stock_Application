package com.example.eggmoney

import android.content.Intent
import android.database.Cursor
import android.os.Build
import android.os.Bundle
import android.provider.ContactsContract
import android.provider.Telephony.Mms.Addr.CONTACT_ID
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_search_result.*


class SearchResultActivity : AppCompatActivity() {
    private var sendMsg = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_result)

        stock_name.text = intent.getStringExtra("stock_name")

        activity_search_gift_button.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK);
            intent.data = ContactsContract.CommonDataKinds.Phone.CONTENT_URI


            // TODO: 전화번호부 가져오기
            // TODO: 서버에게 stock_name 혹은 종목코드로 선물 요청 보내기
            // TODO: 난수 코드 받기
            // TODO: 전화번호 받으면 그걸로 SMS로 난수코드 보내기
        }
    }


}


