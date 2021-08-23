package com.example.myapplication.api.listeners

import android.graphics.Bitmap

interface IObtainImageListener : IBaseOperationListener {
    fun onObtainImage(bitmap: Bitmap)
}