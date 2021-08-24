package com.example.myapplication.repositories

import android.graphics.Bitmap
import com.example.myapplication.api.listeners.IObtainImageListener
import com.example.myapplication.api.operations.ImageAPIOperation

object ImageRepository {
    private val resources = mutableMapOf<String, Bitmap>()

    fun obtainImage(url: String, width: Int, height: Int, listener: IObtainImageListener) {
        if (resources.containsKey(url)) {
            resources[url]!!.let { listener.onObtainImage(it) }
        } else {
            ImageAPIOperation.obtainImage(url, width, height, object : IObtainImageListener {
                override fun onObtainImage(bitmap: Bitmap) {
                    resources[url] = bitmap
                    listener.onObtainImage(bitmap)
                }

                override fun onFailure(exception: Exception) = listener.onFailure(exception)
            })
        }
    }

}