package com.demo.trendingrepozomato.view_model

import androidx.lifecycle.MutableLiveData
import com.demo.trendingrepozomato.models.TrendingRepoResponse
import com.gojeck.trendingrepo.main_screen.utils.BaseViewModel
import com.demo.trendingrepozomato.utils.MyApplication


/**
 * Created by Ajeet Singh on 29/03/20.
 */
class MainViewModel(application: MyApplication) : BaseViewModel(application) {
    val repoResponse = MutableLiveData<ArrayList<TrendingRepoResponse>>()
}