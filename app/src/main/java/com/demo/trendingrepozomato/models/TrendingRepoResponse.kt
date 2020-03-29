package com.demo.trendingrepozomato.models

import com.google.gson.annotations.SerializedName

data class TrendingRepoResponse(

    var id:Int=0,

    @field:SerializedName("forks")
    var forks: Int? = null,

    @field:SerializedName("author")
    var author: String? = null,

    @field:SerializedName("name")
    var name: String? = null,

    @field:SerializedName("description")
    var description: String? = null,

    @field:SerializedName("language")
    var language: String? = null,

    @field:SerializedName("avatar")
    var avatar: String? = null,

    @field:SerializedName("languageColor")
    var languageColor: String? = null,

    @field:SerializedName("stars")
    var stars: Int? = null


)