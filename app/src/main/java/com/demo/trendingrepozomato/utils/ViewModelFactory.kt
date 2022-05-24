package com.demo.trendingrepozomato.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.demo.trendingrepozomato.view_model.MainViewModel


/**
 * Created by Ajeet Singh on 29/3/20.
 */
class ViewModelFactory(val application: MyApplication) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(application) as T
        } else {
            throw IllegalArgumentException("ViewModel not found")
        }
    }


}