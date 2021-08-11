package com.example.myapplication.api.listeners

import com.example.myapplication.api.results.APIResult
import com.example.myapplication.api.results.Contributor

interface IObtainContributorsListener : IBaseOperationListener {
    fun onObtainContributors(result: APIResult<Array<Contributor>?>)
}