package com.app.newsfeed.model

/**
 * News Feed Details Model to map json data
 */
data class NewsFeedDetails(
    var header: String? = null,
    var newsList: ArrayList<NewsDetail> = ArrayList<NewsDetail>()
) {


}