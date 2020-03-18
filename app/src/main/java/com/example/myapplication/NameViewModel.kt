package com.example.myapplication

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class NameViewModel: ViewModel(){
    // Create a LiveData with a String
    val currentName: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    val prefs: MutableLiveData<SharedPreferences> by lazy {
        MutableLiveData<SharedPreferences>()
    }
}