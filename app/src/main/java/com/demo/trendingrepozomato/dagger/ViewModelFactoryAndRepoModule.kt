package com.demo.trendingrepozomato.dagger

import android.content.Context
import androidx.room.Room
import com.demo.trendingrepozomato.utils.ApiInterface
import com.demo.trendingrepozomato.utils.MyApplication
import com.demo.trendingrepozomato.utils.ViewModelFactory
import com.demo.trendingrepozomato.room.TrendingRepoDatabase
import com.demo.trendingrepozomato.view_model.Repository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


/**
 * Created by Ajeet Singh on 29/3/20.
 */
@Module
@Singleton
class ViewModelFactoryAndRepoModule {

    /**
     * this provider will provide the ViewModelFactory that will pass the desired params
     * to the ViewModels
     */
    @Provides
    @Singleton
    fun getViewModelFactory(application: MyApplication): ViewModelFactory {
        return ViewModelFactory(application)
    }

    @Provides
    @Singleton
    fun getDatabase(context: Context): TrendingRepoDatabase {
        return Room.databaseBuilder(
            context,
            TrendingRepoDatabase::class.java, "trending-repo-database"
        ).allowMainThreadQueries().build()
    }

    /**
     * This provider will provide the Repository response that will be responsible to handle the
     * Server and local repo requests
     */
    @Provides
    @Singleton
    fun getRepositoryClass(apiInterface: ApiInterface, database: TrendingRepoDatabase): Repository {
        return Repository(apiInterface, database)
    }


}