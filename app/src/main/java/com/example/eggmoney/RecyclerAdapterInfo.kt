package com.example.eggmoney

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_recyclerview.view.*

//import kotlinx.android.synthetic.main.list_friends.view.*

class RecyclerAdapterInfo(private val items: ArrayList<Info>) :
    RecyclerView.Adapter<RecyclerAdapterInfo.ViewHolder>() {

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: RecyclerAdapterInfo.ViewHolder, position: Int) {
        val item = items[position]
        val listener = View.OnClickListener {it ->
            Toast.makeText(it.context, "Clicked:"+item.ctent, Toast.LENGTH_SHORT).show()
        }
        holder.apply {
            bind(listener, item)
            itemView.tag = item
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerAdapterInfo.ViewHolder {
        val inflatedView = LayoutInflater.from(parent.context).inflate(R.layout.item_recyclerview, parent, false)
        return RecyclerAdapterInfo.ViewHolder(inflatedView)
    }

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        private var view: View = v
        fun bind(listener: View.OnClickListener, item: Info) {
            view.info_Title.text = item.title
            view.info_Content.text = item.ctent
            view.setOnClickListener(listener)
        }
    }
}