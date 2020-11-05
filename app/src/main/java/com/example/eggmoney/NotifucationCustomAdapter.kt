package com.example.eggmoney

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.notification_item_recycler.view.*
import java.text.SimpleDateFormat

class NotifucationCustomAdapter: RecyclerView.Adapter<Holder>(){
    var listData = mutableListOf<notificationItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.present_notification_recyclerview, parent, false)
        return Holder(view)
    }

    override fun getItemCount(): Int {
        return listData.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val notification = listData.get(position)
    }
}

class Holder(itemView: View): RecyclerView.ViewHolder(itemView){
    fun setNotification(notification: notificationItem){
//        itemView.notification_name.text = "${notification.name}"
//        itemView.notification_text.text = notification.text
//
//        var sdf = SimpleDateFormat("yyyy/MM/dd")
//        var formattedDate = sdf.format(notification.timestamp)
//        itemView.notification_date.text = formattedDate
    }
}