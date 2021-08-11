package com.example.myapplication.api

import android.content.Context
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley

enum class APIManager {
    API;

    private var queue: RequestQueue? = null
    fun init(context: Context) {
        queue = Volley.newRequestQueue(context)
    }

    fun putRequest(request: Request<*>) = queue?.add(request)
}