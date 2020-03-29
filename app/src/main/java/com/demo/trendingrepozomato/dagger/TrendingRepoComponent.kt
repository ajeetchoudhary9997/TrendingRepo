package com.demo.trendingrepozomato.dagger

import com.demo.trendingrepozomato.BaseActivity
import com.demo.trendingrepozomato.utils.MyApplication
import com.gojeck.trendingrepo.main_screen.utils.BaseViewModel
import dagger.Component
import javax.inject.Singleton


/**
 * Created by Ajeet Singh on 29/3/20.
 */
@Singleton
@Component(modules = [MainScreenModule::class])
interface TrendingRepoComponent {

    fun inject(application: MyApplication)

    fun inject(activity: BaseActivity)

    fun inject(baseViewModel: BaseViewModel)
}