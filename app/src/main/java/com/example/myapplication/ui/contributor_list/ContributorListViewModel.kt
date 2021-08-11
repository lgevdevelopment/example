package com.example.myapplication.ui.contributor_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.myapplication.api.listeners.IObtainContributorsListener
import com.example.myapplication.api.operations.ObtainContributorsOperation
import com.example.myapplication.api.results.APIResult
import com.example.myapplication.api.results.Contributor
import com.example.myapplication.ui.main.MainViewModel

class ContributorListViewModel : MainViewModel(), IObtainContributorsListener {
    private val _contributors = MutableLiveData<List<Contributor>>(emptyList())
    val contributors: LiveData<List<Contributor>> = _contributors

    override fun onObtainContributors(result: APIResult<Array<Contributor>?>) {
        result.result?.let {
            _contributors.postValue(it.asList())
        }
    }

    override fun onFailure(exception: Exception) {
        TODO("Not yet implemented")
    }

    init {
        ObtainContributorsOperation.obtainContributors(this)
    }

}