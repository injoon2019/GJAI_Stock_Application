package com.eggmoney.scone

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView



class NotifucationCustomAdapter(
    val context: Context,
    val notification_list: ArrayList<NotificationItem>) : RecyclerView.Adapter<NotifucationCustomAdapter.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(context).inflate(R.layout.present_notification_recyclerview, parent, false)
        return Holder(view)
    }

    override fun getItemCount(): Int {
        return notification_list.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder?.bind(notification_list[position], context)
    }

    inner class Holder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {
        val notification_name = itemView?.findViewById<TextView>(R.id.notification_name)
        val notification_text_1 = itemView?.findViewById<TextView>(R.id.notification_text_1)
        val notification_text_2 = itemView?.findViewById<TextView>(R.id.notification_text_2)
        val notification_timestamp = itemView?.findViewById<TextView>(R.id.notification_timestamp)

        fun bind(NotificationItem: NotificationItem, context: Context) {
            notification_name?.text = NotificationItem.name
            notification_text_1?.text = NotificationItem.text_1
            notification_text_2?.text = NotificationItem.text_2
            notification_timestamp?.text = NotificationItem.timestamp
        }
    }



}
