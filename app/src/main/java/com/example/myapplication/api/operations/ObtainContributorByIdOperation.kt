package com.example.myapplication.api.operations

import com.android.volley.Request
import com.example.myapplication.api.listeners.IObtainContributorByIdListener
import com.example.myapplication.api.results.APIResult
import com.example.myapplication.api.results.Contributor

class ObtainContributorByIdOperation private constructor(
    contributorId: String,
    listener: IObtainContributorByIdListener
) :
    JsonAPIOperation<Contributor, IObtainContributorByIdListener>(listener) {
    override val classOfValue = Contributor::class.java
    override val url = "https://api.github.com/users/$contributorId"
    override val method = Request.Method.GET

    override fun onSuccess(result: APIResult<Contributor?>) = listener.onObtainContributorById(result.result!!)

    companion object {
        fun obtainContributorById(contributorId: String, listener: IObtainContributorByIdListener) =
            putRequest(ObtainContributorByIdOperation(contributorId, listener).buildRequest())
    }
}