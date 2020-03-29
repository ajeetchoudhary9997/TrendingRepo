package com.demo.trendingrepozomato.utils

import android.content.SharedPreferences
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.demo.trendingrepozomato.utils.MyApplication
import com.demo.trendingrepozomato.view_model.Repository
import javax.inject.Inject


/**
 * Created by Ajeet Singh on 29/3/20.
 */
 abstract class BaseViewModel(application: MyApplication) : ViewModel() {

    @Inject
    lateinit var repository: Repository
    @Inject
    lateinit var sharedPreferences: SharedPreferences


    var isLoading = ObservableField<Boolean>() // will be true when loading data from local repo or server false otherwise

    var isRequestFailed = ObservableField<Boolean>() // will be true is loading failed for any reason false otherwise

    init {
        application.component.inject(baseViewModel = this)
        isLoading.set(false)
        isRequestFailed.set(false)
    }

}