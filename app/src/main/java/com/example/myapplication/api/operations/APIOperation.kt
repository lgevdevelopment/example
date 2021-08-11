package com.example.myapplication.api.operations

import android.text.TextUtils.isEmpty
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.VolleyError
import com.example.myapplication.api.APIManager
import com.example.myapplication.api.listeners.IBaseOperationListener
import com.example.myapplication.api.results.APIResult
import com.example.myapplication.api.results.Result
import com.example.myapplication.utils.HLog.debug
import com.example.myapplication.utils.HLog.error
import com.example.myapplication.utils.Utils.EMPTY
import com.google.gson.Gson

abstract class APIOperation<R : APIResult<*>, T, L : IBaseOperationListener?> internal constructor(val listener: L) : Operation<R>(), Response.Listener<T>, Response.ErrorListener {
    override fun onErrorResponse(volleyError: VolleyError) {
        error(tag, "onErrorResponse " + if (null != volleyError.networkResponse) volleyError.networkResponse.statusCode.toString() + " " + String(volleyError.networkResponse.data) else if (isEmpty(volleyError.message)) EMPTY else volleyError.message)
        onFailure(buildResult(volleyError))
    }

    override fun onResponse(response: T) {
        debug(tag, "onResponse $response")
        val result = buildResult(response)
        if (result.isSuccess) {
            debug(tag, "onSuccess")
            onSuccess(result)
        } else onFailure(result)
    }

    protected abstract val url: String
    protected abstract fun buildResult(volleyError: VolleyError?): R
    protected abstract fun buildResult(response: T?): R
    abstract fun buildRequest(): Request<*>?
    override fun onStart() = Unit
    override fun onFailure(result: R) {
        error(tag, "onFailure ", result.exception)
        listener?.onFailure(result.exception!!)
    }

    companion object {
        val GSON = Gson()
        fun putRequest(request: Request<*>) {
            debug("APIOperation", "putRequest $request")
            APIManager.API.putRequest(request)
        }
    }
}

abstract class Operation<R : Result<*>> {
    protected val tag = javaClass.simpleName

    protected abstract fun onStart()
    protected abstract fun onSuccess(result: R)
    protected abstract fun onFailure(result: R)
}