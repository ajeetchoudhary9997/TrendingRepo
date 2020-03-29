package com.demo.trendingrepozomato.utils

import android.content.ContextWrapper
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.bumptech.glide.Glide
import com.demo.trendingrepozomato.models.TrendingRepoResponse
import com.demo.trendingrepozomato.view.adaptor.TrendingRepoAdaptor


/**
 * Created by Ajeet Singh on 29/3/20.
 */

fun View.getParentActivity(): AppCompatActivity? {
    var context = this.context
    while (context is ContextWrapper) {
        if (context is AppCompatActivity) {
            return context
        }
        context = context.baseContext
    }
    return null
}

@BindingAdapter("bindAdaptor")
fun setAdaptor(
    recyclerView: RecyclerView,
    trendingRepoResponse: MutableLiveData<ArrayList<TrendingRepoResponse>>
) {
    recyclerView.getParentActivity()?.let { activity ->
        val linearLayoutManager = LinearLayoutManager(activity)
        recyclerView.layoutManager = linearLayoutManager
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                activity,
                linearLayoutManager.orientation
            )
        )
        trendingRepoResponse.observe(activity, Observer {
            it?.let {
                recyclerView.adapter = TrendingRepoAdaptor(it)
            }
        })
    }
}

@BindingAdapter("loadImage")
fun loadImage(
    imageView: ImageView,
    imgUrl: String
) {
    Glide.with(imageView.context).load(imgUrl).into(imageView)

}

@BindingAdapter("setColor")
fun setColor(
    linearLayout: LinearLayout,
    color: String?
) {
    var parseColor = Color.BLACK
    try {
        parseColor = Color.parseColor(color)
    } catch (e: Exception) {
    }
    val gd = GradientDrawable()
    gd.setColor(parseColor)
    gd.cornerRadius = 50f
    try {
        linearLayout.background = gd
    } catch (e: java.lang.Exception) {
    }

}

@BindingAdapter("refresh")
fun setRefresh(
    swipeRefreshLayout: SwipeRefreshLayout,
    refreshing: MutableLiveData<Boolean>
) {
    swipeRefreshLayout.getParentActivity()?.let { activity ->
        refreshing.observe(activity, Observer {
            it?.let {
                swipeRefreshLayout.isRefreshing = it
            }
        })
    }
}