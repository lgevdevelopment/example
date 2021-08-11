package com.example.myapplication.api.operations

import android.text.TextUtils
import com.android.volley.Request
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.example.myapplication.api.listeners.IBaseOperationListener
import com.example.myapplication.api.results.APIResult
import com.example.myapplication.utils.HLog.debug

abstract class JsonAPIOperation<V, L : IBaseOperationListener?> internal constructor(listener: L) : APIOperation<APIResult<V?>, String?, L>(listener) {
    override fun buildRequest(): Request<*> {
        debug(tag, "buildRequest $url")
        return StringRequest(method, url, this, this)
    }

    open val method = Request.Method.POST
    override fun buildResult(volleyError: VolleyError?): APIResult<V?> = APIResult(volleyError)
    override fun buildResult(response: String?) = APIResult(if (TextUtils.isEmpty(response) || null == classOfValue) null else GSON.fromJson(response, classOfValue))
    abstract val classOfValue: Class<V>?
}