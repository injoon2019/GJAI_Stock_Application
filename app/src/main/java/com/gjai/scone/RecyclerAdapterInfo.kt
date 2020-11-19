package com.gjai.scone

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.gjai.scone.Fragments.MainFragment

//class RecyclerAdapterInfo(val context: MainlayoutActivity, val infoList: Array<Info>) :
//RecyclerView.Adapter<RecyclerAdapterInfo.Holder>() {
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
//        val view = LayoutInflater.from(context).inflate(R.layout.item_recyclerview, parent, false)
//        return Holder(view)
//    }
//
//    override fun getItemCount(): Int {
//        return infoList.size
//    }
//
//    override fun onBindViewHolder(holder: Holder, position: Int) {
//        holder?.bind(infoList[position], context)
//    }
//
//    inner class Holder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {
//        private val mainTitle = itemView?.findViewById<TextView>(R.id.info_Title)
//        private val mainContent = itemView?.findViewById<TextView>(R.id.info_Content)
//        private val mainImage = itemView?.findViewById<ImageView>(R.id.info_ImageView)
//
//        fun bind(info: Info, context: Context) {
//            /* dogPhoto의 setImageResource에 들어갈 이미지의 id를 파일명(String)으로 찾고,
//            이미지가 없는 경우 안드로이드 기본 아이콘을 표시한다.*/
//            if (info.main_image != "") {
//                val resourceId = context.resources.getIdentifier(
//                    info.main_image,
//                    "drawable",
//                    context.packageName
//                )
//                mainImage?.setImageResource(resourceId)
//            } else {
//                mainImage?.setImageResource(R.mipmap.ic_launcher)
//            }
//            /* 나머지 TextView와 String 데이터를 연결한다. */
//            mainTitle?.text = info.main_title
//            mainContent?.text = info.main_content
//
//
//        }
//    }
//}
//
//
