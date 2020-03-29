package com.demo.trendingrepozomato.view_model

import com.demo.trendingrepozomato.models.TrendingRepoResponse
import com.demo.trendingrepozomato.room.TrendingRepoDatabase
import com.demo.trendingrepozomato.utils.ApiInterface


/**
 * Created by Ajeet Singh on 18/1/20.
 */
/**
 * This class will handle the local repo operation and server operation
 */
class Repository(
    private val apiInterface: ApiInterface,
    private val database: TrendingRepoDatabase
) {

    /**
     * This method will call an API and load data from the server
     * Lambda expression are for notify the viewModel about the operation status
     */
    suspend fun loadDataFromServer(
        success: (ArrayList<TrendingRepoResponse>) -> Unit,
        failed: (String?) -> Unit
    ) {
        try {
            val arrayList = apiInterface.getTrendingRepo().await()
            success(arrayList)
        } catch (e: Exception) {
            failed(e.message)
        }
    }

    /**
     * this method will load trending repo from the local database
     */
    suspend fun loadDataFromLocal(
        success: (ArrayList<TrendingRepoResponse>) -> Unit,
        failed: (String?) -> Unit
    ) {
        try {
            val arrayList =
                database.getTrendingRepoDao().getAllTrendingRepo() as ArrayList<TrendingRepoResponse>
            success(arrayList)
        } catch (e: Exception) {
            failed(e.message)
        }
    }

    /**
     * this method will insert data into the local database
     */
    suspend fun insertDataIntoLocal(
        trendingRepos: ArrayList<TrendingRepoResponse>,
        success: () -> Unit,
        failed: (String?) -> Unit
    ) {
        try {
                database.getTrendingRepoDao().insertTrendingRepos(trendingRepos)
            success()
        } catch (e: Exception) {
            failed(e.message)
        }
    }

    /**
     * This method will delete all data from the trending repo table
     */
    suspend fun deleteAllTrendingRepoFromLocal(
        success: () -> Unit,
        failed: (String?) -> Unit
    ) {
        try {
            database.getTrendingRepoDao().deleteAllTrendingRepo()
            success()
        } catch (e: Exception) {
            failed(e.message)
        }
    }
}