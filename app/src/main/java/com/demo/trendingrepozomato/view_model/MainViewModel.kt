package com.demo.trendingrepozomato.view_model

import androidx.lifecycle.MutableLiveData
import com.demo.trendingrepozomato.models.TrendingRepoResponse
import com.demo.trendingrepozomato.utils.BaseViewModel
import com.demo.trendingrepozomato.utils.MyApplication
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


/**
 * Created by Ajeet Singh on 29/03/20.
 */
class MainViewModel(application: MyApplication) : BaseViewModel(application) {
    val repoResponse = MutableLiveData<ArrayList<TrendingRepoResponse>>()
    val isSwipeRefresh = MutableLiveData<Boolean>()
    private val LOCAL_CACHE_TIME = 2 // in hours


    fun loadTrendingRepoData() {
        if (ceckIfLocalRepoExpire()) {
            deleteTrendingRepoDataFromLocal()
        } else {
            loadTrendingRepoDataFromLocal()
        }
    }


    /**
     * This function will load from from server forcefully
     * */
    fun loadAfterSwipeRefreshLayout() {
        isSwipeRefresh.value = true
        loadTrendingRepoDataFromServer()
    }

    /**
     * Load trending repo from the server
     */
    private fun loadTrendingRepoDataFromServer() {
        isRequestFailed.set(false)
        val success: (ArrayList<TrendingRepoResponse>) -> Unit = { response ->
            insertTrendingRepoDataIntoLocal(response)
        }
        val failed: (String?) -> Unit = {
            isLoading.set(false)
            isRequestFailed.set(true)
        }
        CoroutineScope(Dispatchers.Main).launch {
            repository.loadDataFromServer(success, failed)
        }
    }

    /**
     * Load TrendingRepo Data from the local repo and update the related live data
     */
    private fun loadTrendingRepoDataFromLocal() {
        isLoading.set(true)
        val success: (ArrayList<TrendingRepoResponse>) -> Unit = { response ->
            repoResponse.value = response
            isLoading.set(false)
            isRequestFailed.set(false)
        }
        val failed: (String?) -> Unit = {
            isLoading.set(false)
            isRequestFailed.set(true)
        }
        CoroutineScope(Dispatchers.Main).launch {
            repository.loadDataFromLocal(success, failed)
        }
    }

    /**
     * Load TrendingRepo Data from the local repo and update the related live data
     */
    private fun deleteTrendingRepoDataFromLocal() {
        val success: () -> Unit = {
            deleteCacheTime()
            isLoading.set(true)
            loadTrendingRepoDataFromServer()
        }
        val failed: (String?) -> Unit = {
            isLoading.set(false)
            isRequestFailed.set(true)
        }
        CoroutineScope(Dispatchers.Main).launch {
            repository.deleteAllTrendingRepoFromLocal(success, failed)
        }
    }

    /**
     * insert TrendingRepo Data into the local repo and update the related live data
     */
    private fun insertTrendingRepoDataIntoLocal(trendingRepos: ArrayList<TrendingRepoResponse>) {
        val success: () -> Unit = {
            saveCacheTime()
            repoResponse.value = trendingRepos
            isLoading.set(false)
            isSwipeRefresh.value = false
            isRequestFailed.set(false)
        }
        val failed: (String?) -> Unit = {
            isLoading.set(false)
            isRequestFailed.set(true)
        }
        CoroutineScope(Dispatchers.Main).launch {
            repository.insertDataIntoLocal(trendingRepos, success, failed)
        }
    }

    /**
     * this function will return true if local cache time is more then the expiry time or
     * no cache there, false otherwise
     *
     */
    private fun ceckIfLocalRepoExpire(): Boolean {
        if (!sharedPreferences.contains("cache_time")) return true
        return (System.currentTimeMillis() - sharedPreferences.getLong(
            "cache_time",
            0L
        )) > (LOCAL_CACHE_TIME * 60 * 60 * 1000)
    }

    private fun saveCacheTime() {
        sharedPreferences.edit().putLong("cache_time", System.currentTimeMillis()).apply()
    }

    private fun deleteCacheTime() {
        sharedPreferences.edit().remove("cache_time").apply()
    }}