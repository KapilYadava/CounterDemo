package com.example.myapplication

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.conter_two_fragment.view.*

class Counter2Fragment: Fragment(){

    private lateinit var model: NameViewModel
    private lateinit var v: View
    private lateinit var prefs: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = View.inflate(context, R.layout.conter_two_fragment, null)
        setUp()
        return v
    }

    private fun setUp(){
        prefs = context!!.getSharedPreferences("MY_PREFS", Context.MODE_PRIVATE)
        var counter = prefs.getInt("COUNT", 0)
        v.counter.text = counter.toString()

        model = ViewModelProvider(activity!!).get(NameViewModel::class.java)
        val counterObserver = Observer<Any> { newName ->
            val pref = newName as SharedPreferences
            v.counter.text = pref.getInt("COUNT", 0).toString()
        }

        model.prefs.observe(activity!!, counterObserver as Observer<in SharedPreferences>)
        v.mins.setOnClickListener { view ->
            counter--
            prefs.edit().putInt("COUNT", counter).commit()
            model.prefs.value = prefs
        }
        v.plus.setOnClickListener { view ->
            counter++
            prefs.edit().putInt("COUNT", counter).commit()
            model.prefs.value = prefs
        }
    }
}