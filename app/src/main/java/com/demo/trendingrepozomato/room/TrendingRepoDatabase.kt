package com.demo.trendingrepozomato.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.demo.trendingrepozomato.models.TrendingRepoResponse
import com.demo.trendingrepozomato.room.TrendingRepoDao


/**
 * Created by Ajeet Singh on 29/3/20.
 */
@Database(entities = arrayOf(TrendingRepoResponse::class), version = 1)
abstract class TrendingRepoDatabase : RoomDatabase() {
    abstract fun getTrendingRepoDao(): TrendingRepoDao
}