package com.example.myapplication.api.operations

import android.graphics.Bitmap
import android.widget.ImageView
import com.android.volley.Request
import com.android.volley.VolleyError
import com.android.volley.toolbox.ImageRequest
import com.example.myapplication.api.listeners.IObtainImageListener
import com.example.myapplication.api.results.APIResult
import com.example.myapplication.utils.HLog

class ImageAPIOperation internal constructor(listener: IObtainImageListener, override val url: String, private val width : Int, private val height: Int) : APIOperation<APIResult<Bitmap>, Bitmap, IObtainImageListener>(listener) {
    override fun buildRequest(): Request<*> {
        HLog.debug(tag, "buildRequest $url")
        return ImageRequest(url, this, width, height, ImageView.ScaleType.FIT_XY, Bitmap.Config.ARGB_8888, this)
    }

    override fun buildResult(volleyError: VolleyError?): APIResult<Bitmap> = APIResult(volleyError)
    override fun buildResult(response: Bitmap?): APIResult<Bitmap> = APIResult(response!!)

    override fun onSuccess(result: APIResult<Bitmap>) {
        result.result?.let { listener.onObtainImage(it) }
    }

    companion object {
        fun obtainImage(url: String, width: Int, height: Int, listener: IObtainImageListener) =
            putRequest(ImageAPIOperation(listener, url, width, height).buildRequest())
    }
}