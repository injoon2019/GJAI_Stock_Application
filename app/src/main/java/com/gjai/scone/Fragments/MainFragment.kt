package com.gjai.scone.Fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.constraintlayout.widget.ConstraintLayout
import com.gjai.scone.NotificationActivity
import com.gjai.scone.R
import kotlinx.android.synthetic.main.activity_main_layout.*

class MainFragment : Fragment() {

    private lateinit var maincardinfo: ConstraintLayout
    companion object {
        const val TAG : String = "로그"

        fun newInstance() : MainFragment{
            return MainFragment()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            Log.d(TAG, "MainFragment - onCreate() called")
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d(TAG, "MainFragment - onAttach() called")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Log.d(TAG, "MainFragment - onCreateView() called")

        val view = inflater.inflate(R.layout.activity_main_layout, container, false)
        val maincardinfo = view?.findViewById<ConstraintLayout>(R.id.main_info_card)
        maincardinfo?.setOnClickListener {
            v -> startActivity(Intent(getActivity(), NotificationActivity::class.java))
        }

        return view
    }
}