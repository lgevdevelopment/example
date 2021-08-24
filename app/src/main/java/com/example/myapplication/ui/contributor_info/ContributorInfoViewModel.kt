package com.example.myapplication.ui.contributor_info

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.myapplication.api.listeners.IObtainContributorByIdListener
import com.example.myapplication.api.results.Contributor
import com.example.myapplication.repositories.ContributorRepository
import com.example.myapplication.ui.main.MainViewModel

class ContributorInfoViewModel : MainViewModel() {
    private val _selectedContributor = MutableLiveData<Contributor?>()
    val selectedContributor: LiveData<Contributor?> = _selectedContributor

    fun selectContributor(contributorId: String) {
        ContributorRepository.obtainContributorById(contributorId, object : IObtainContributorByIdListener {
            override fun onObtainContributorById(result: Contributor) {
                _selectedContributor.value = result
            }

            override fun onFailure(exception: Exception) {
                TODO("Not yet implemented")
            }
        })
    }
}