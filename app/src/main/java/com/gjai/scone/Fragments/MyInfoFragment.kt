package com.gjai.scone.Fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gjai.scone.R

class MyInfoFragment : Fragment() {
    companion object {
        const val TAG : String = "로그"

        fun newInstance() : MyInfoFragment{
            return MyInfoFragment()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            Log.d(TAG, "MyInfoFragment - onCreate() called")
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d(TAG, "MyInfoFragment - onAttach() called")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Log.d(TAG, "MyInfoFragment - onCreateView() called")
        val view = inflater.inflate(R.layout.activity_myinfo, container, false)
        return view
    }
}