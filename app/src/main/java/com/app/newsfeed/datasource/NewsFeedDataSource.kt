package com.app.newsfeed.datasource

import android.content.Context
import android.util.Log
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.app.newsfeed.model.NewsFeedDetails
import com.app.newsfeed.parser.NewsFeedParser
import com.app.newsfeed.responselistener.ResponseErrorListener
import com.app.newsfeed.responselistener.ResponseListener

/**
 * News details Data Source implmentation
 * Using Volley library to request server for fetching news details list ,
 * parse the data and return to repository
 */
class NewsFeedDataSource(context: Context) : DataSource {

    private val url = "https://dl.dropboxusercontent.com/s/2iodh4vg0eortkl/facts.json"
    private var context: Context = context

    override fun getNewsDetailsList(
        responseListener: ResponseListener<NewsFeedDetails>,
        errorListener: ResponseErrorListener
    ) {

        val jsonRequest = JsonObjectRequest(
            Request.Method.GET, url, null,
            Response.Listener { response ->
                responseListener.onResponse(NewsFeedParser.parseNewsFeed(response))
            },
            Response.ErrorListener {
                Log.i("ERROR :: ", "Error fetching response  ")
                errorListener.sendErrorMessage();

            })

        // Add the request to the RequestQueue.
        Volley.newRequestQueue(context)
            .add(jsonRequest)
    }

}