package com.example.myapplication.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.myapplication.api.listeners.IObtainContributorByIdListener
import com.example.myapplication.api.operations.ObtainContributorByIdOperation
import com.example.myapplication.api.results.Contributor

object ContributorRepository {
    private val contributors = mutableMapOf<String, Contributor>()

    fun obtainContributorById(contributorId: String, listener: IObtainContributorByIdListener) {
        if (contributors.containsKey(contributorId)) {
            contributors[contributorId]!!.let { listener.onObtainContributorById(it) }
        } else {
            ObtainContributorByIdOperation.obtainContributorById(contributorId, object : IObtainContributorByIdListener {
                override fun onObtainContributorById(result: Contributor) {
                    contributors[contributorId] = result
                    listener.onObtainContributorById(result)
                }

                override fun onFailure(exception: Exception) = listener.onFailure(exception)
            })
        }
    }
}