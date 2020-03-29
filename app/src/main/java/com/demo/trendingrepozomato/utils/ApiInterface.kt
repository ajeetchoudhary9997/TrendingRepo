package com.demo.trendingrepozomato.utils

import com.demo.trendingrepozomato.models.TrendingRepoResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query


/**
 * Created by Ajeet Singh on 29/3/20.
 */
interface ApiInterface {
    @GET("/repositories")
    fun getTrendingRepo(@Query("since") since: String = "daily"): Deferred<ArrayList<TrendingRepoResponse>>
}