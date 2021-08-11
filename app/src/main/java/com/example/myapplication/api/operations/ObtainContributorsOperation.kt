package com.example.myapplication.api.operations

import com.android.volley.Request
import com.example.myapplication.api.listeners.IObtainContributorsListener
import com.example.myapplication.api.results.APIResult
import com.example.myapplication.api.results.Contributor

class ObtainContributorsOperation private constructor(listener: IObtainContributorsListener) :
    JsonAPIOperation<Array<Contributor>, IObtainContributorsListener>(listener) {
    override val classOfValue = Array<Contributor>::class.java
    override val url = "https://api.github.com/users"
    override val method = Request.Method.GET

    override fun onSuccess(result: APIResult<Array<Contributor>?>) = listener.onObtainContributors(result)

    companion object {
        fun obtainContributors(listener: IObtainContributorsListener) =
            putRequest(ObtainContributorsOperation(listener).buildRequest())
    }
}