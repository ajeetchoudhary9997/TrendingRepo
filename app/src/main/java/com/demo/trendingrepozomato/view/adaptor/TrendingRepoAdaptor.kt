package com.demo.trendingrepozomato.view.adaptor

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.demo.trendingrepozomato.R
import com.demo.trendingrepozomato.databinding.HomeRecyclerRowLayoutBinding
import com.demo.trendingrepozomato.models.TrendingRepoResponse
import kotlinx.android.synthetic.main.home_recycler_row_layout.view.*


/**
 * Created by Ajeet Singh on 29/3/20.
 */
class TrendingRepoAdaptor(private val trendingRepoList: ArrayList<TrendingRepoResponse>) :
    RecyclerView.Adapter<TrendingRepoAdaptor.ViewHolder>() {
    private lateinit var layoutInflater: LayoutInflater
    var selectedIndex: Int = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        if (!::layoutInflater.isInitialized)
            layoutInflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<HomeRecyclerRowLayoutBinding>(
            layoutInflater,
            R.layout.home_recycler_row_layout,
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun getItemCount() = trendingRepoList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val trendingRepoResponse = trendingRepoList[position]

        holder.binding.root.let { view ->
            view.repo_bottom_group.visibility =
                if (trendingRepoResponse.isExpend) View.VISIBLE else View.GONE
            view.setOnClickListener {
                if (selectedIndex == -1) {
                    view.repo_bottom_group.visibility = View.VISIBLE
                    trendingRepoResponse.isExpend = true
                    selectedIndex = position
                } else if (selectedIndex == position) {
                    view.repo_bottom_group.visibility = View.GONE
                    trendingRepoResponse.isExpend = false
                    selectedIndex = -1
                } else {
                    view.repo_bottom_group.visibility = View.VISIBLE
                    trendingRepoResponse.isExpend = true
                    trendingRepoList[selectedIndex].isExpend = false
                    notifyItemChanged(selectedIndex)
                    selectedIndex = position
                }
            }
        }
        holder.binding.trendingRepo = trendingRepoResponse
    }

    class ViewHolder(val binding: HomeRecyclerRowLayoutBinding) :
        RecyclerView.ViewHolder(binding.root)
}