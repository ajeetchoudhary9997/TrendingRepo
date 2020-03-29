package com.demo.trendingrepozomato.view

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.demo.trendingrepozomato.BaseActivity
import com.demo.trendingrepozomato.R
import com.demo.trendingrepozomato.databinding.ActivityMainBinding
import com.demo.trendingrepozomato.view_model.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)
        if (::binding.isInitialized)
            binding.mainViewModel =
                ViewModelProviders.of(this, viewModelFactory).get(MainViewModel::class.java)
        if (::binding.isInitialized)
            binding.mainViewModel?.apply {
                if (isLoading.get() == false && repoResponse.value?.size ?: 0 == 0)
                    loadTrendingRepoData()
            }

        pull_to_refresh_layout.setOnRefreshListener {
            binding.mainViewModel?.apply {
                if (isLoading.get() == false)
                    loadAfterSwipeRefreshLayout()
            }
        }
    }
}
