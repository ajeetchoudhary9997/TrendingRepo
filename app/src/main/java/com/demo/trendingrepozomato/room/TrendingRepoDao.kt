package com.demo.trendingrepozomato.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.demo.trendingrepozomato.models.TrendingRepoResponse


/**
 * Created by Ajeet Singh on 29/2/20.
 */
@Dao
abstract class TrendingRepoDao {

    @Query("SELECT * FROM trendingreporesponse ")
    abstract fun getAllTrendingRepo(): List<TrendingRepoResponse>

    @Query("DELETE FROM trendingreporesponse")
    abstract fun deleteAllTrendingRepo()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertTrendingRepos(trendingRepo: List<TrendingRepoResponse>)
}