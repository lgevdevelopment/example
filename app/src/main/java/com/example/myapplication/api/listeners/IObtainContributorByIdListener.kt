package com.example.myapplication.api.listeners

import com.example.myapplication.api.results.Contributor

interface IObtainContributorByIdListener : IBaseOperationListener {
    fun onObtainContributorById(result: Contributor)
}