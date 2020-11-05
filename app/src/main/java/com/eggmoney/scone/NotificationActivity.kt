package com.eggmoney.scone

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_notification.*
import kotlinx.android.synthetic.main.info_main_layout.*
import kotlinx.android.synthetic.main.notification_item_recycler.*

class NotificationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification)

        //val data:MutableList<notificationItem> = loadData()
        var adapter = NotifucationCustomAdapter()
        //adapter.listData = data

        present_notification_main_Recycle.adapter = adapter
        //present_notification_main_Recycle.layoutManager = LinearLayoutManager(this)
        setContentView(R.layout.notification_item_recycler)

        fun loadData(): MutableList<notificationItem> {
            val data: MutableList<notificationItem> = mutableListOf()
            for (no in 1..10) {
                var name = "병표 님이"
                var title = "선물하신 카카오 주식을 받으셨습니다"
                val date = System.currentTimeMillis()
                var item = notificationItem(name, title, date)
                data.add(item)
            }
            return data
        }
    }
}