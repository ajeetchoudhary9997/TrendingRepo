package com.demo.trendingrepozomato.utils

import android.app.Application
import com.demo.trendingrepozomato.dagger.DaggerTrendingRepoComponent
import com.demo.trendingrepozomato.dagger.MainScreenModule
import com.demo.trendingrepozomato.dagger.TrendingRepoComponent


/**
 * Created by Ajeet Singh on 29/3/20.
 */
class MyApplication : Application() {
    lateinit var component: TrendingRepoComponent

    override fun onCreate() {
        super.onCreate()
        component =
            DaggerTrendingRepoComponent
                .builder()
                .mainScreenModule(MainScreenModule(this))
                .build()
        if (::component.isInitialized)
            component.inject(this)
    }
}