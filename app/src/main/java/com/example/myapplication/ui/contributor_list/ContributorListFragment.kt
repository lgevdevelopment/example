package com.example.myapplication.ui.contributor_list

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.MainActivity
import com.example.myapplication.R
import com.example.myapplication.ui.main.MainFragment

const val CONTRIBUTOR_ID_EXTRA = "contributorId"

class ContributorListFragment : MainFragment<ContributorListViewModel>(R.layout.fragment_contributor_list) {
    override var viewModel = ContributorListViewModel()
    var contributorsAdapter = ContributorsAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val contributors = view.findViewById<RecyclerView>(R.id.contributors)

        contributors.adapter = contributorsAdapter.apply {
            onClick = {
                (activity as MainActivity).navController.navigate(
                    R.id.navigation_contributor_details,
                    Bundle().apply { putString(CONTRIBUTOR_ID_EXTRA, it.login) }
                )
            }
        }

        viewModel.contributors.observe(viewLifecycleOwner) {
            contributorsAdapter.submitList(it)
        }
    }
}