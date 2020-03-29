package com.demo.trendingrepozomato.dagger

import android.content.Context
import android.content.SharedPreferences
import com.demo.trendingrepozomato.utils.MyApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


/**
 * Created by Ajeet Singh on 29/3/20.
 */
@Module(includes = [RetrofitModule::class, ViewModelFactoryAndRepoModule::class])
@Singleton
class MainScreenModule(private val application:MyApplication) {

    @Provides
    @Singleton
    fun provideApplication():MyApplication{
        return application
    }

    /**
     * This provider will provide the context in the application  where needed
     * NOTE: context will be of MyApplication type
     * */
    @Provides
    @Singleton
    fun provideContext(application:MyApplication): Context {
        return application.applicationContext
    }

    /**
     * This provider will provide the SharedPreferences
     *
     * */
    @Provides
    @Singleton
    fun getSharedPref(context: Context): SharedPreferences {
        return context.getSharedPreferences("trending_repo", Context.MODE_PRIVATE)
    }}