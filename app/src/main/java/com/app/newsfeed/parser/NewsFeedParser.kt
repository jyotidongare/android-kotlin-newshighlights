package com.app.newsfeed.parser

import com.app.newsfeed.model.NewsFeedDetails
import com.app.newsfeed.model.NewsDetail
import org.json.JSONArray
import org.json.JSONObject

/**
 * Parser class to parse Json data
 */
object NewsFeedParser {

    private val title: String = "title"
    private val description: String = "description"
    private val imageHref: String = "imageHref"

    private val newstitle: String = "title"
    private val newsListRows: String = "rows"

    private val blankString: String = ""


    fun parseNewsFeed(jsonObject: JSONObject): NewsFeedDetails {

        val newsFeedDetails = NewsFeedDetails()

        val newsTitle = jsonObject.optString(newstitle)
        val jsonArray = jsonObject.optJSONArray(newsListRows)

        newsFeedDetails.header = newsTitle

        if (jsonArray != null && jsonArray.length() > 0) {
            newsFeedDetails.newsList = getNewsList(jsonArray)
        }

        return newsFeedDetails

    }

    private fun getNewsList(jsonArray: JSONArray): ArrayList<NewsDetail> {

        var newsList = ArrayList<NewsDetail>()

        for (index in 0 until jsonArray.length()) {

            var jsonObject: JSONObject = jsonArray.getJSONObject(index)
            // handling null checks
            if (!jsonObject.isNull(title) && !jsonObject.isNull(description) && !jsonObject.isNull(
                    imageHref
                )
            ) {
                newsList.add(getNewsDetail(jsonObject))
            }
        }

        return newsList
    }

    private fun getNewsDetail(jsonObject: JSONObject): NewsDetail {

        return NewsDetail(
            optData(jsonObject, title),
            optData(jsonObject, description),
            optData(jsonObject, imageHref)
        )
    }


    private fun optData(jsonObject: JSONObject, key: String): String {
        return if (jsonObject.isNull(key)) blankString else jsonObject.optString(
            key,
            blankString
        )
    }


}