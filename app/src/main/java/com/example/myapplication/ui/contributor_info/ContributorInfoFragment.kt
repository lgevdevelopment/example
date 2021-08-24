package com.example.myapplication.ui.contributor_info

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.example.myapplication.R
import com.example.myapplication.api.listeners.IObtainImageListener
import com.example.myapplication.repositories.ImageRepository
import com.example.myapplication.ui.contributor_list.CONTRIBUTOR_ID_EXTRA
import com.example.myapplication.ui.main.MainFragment

class ContributorInfoFragment : MainFragment<ContributorInfoViewModel>(R.layout.fragment_contributor_info) {
    override var viewModel = ContributorInfoViewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        activity?.title = "Contributors"

        val contributorName = view.findViewById<TextView>(R.id.contributor_name)
        val contributorAvatar = view.findViewById<View>(R.id.contributor_avatar)

        arguments?.getString(CONTRIBUTOR_ID_EXTRA)?.let { viewModel.selectContributor(contributorId = it) }

        viewModel.selectedContributor.observe(viewLifecycleOwner) {
            it?.let { contributor ->
                contributorName.text = contributor.login
                ImageRepository.obtainImage(contributor.avatar_url, contributorAvatar.width, contributorAvatar.height, object : IObtainImageListener {
                    override fun onObtainImage(bitmap: Bitmap) {
                        contributorAvatar.background = BitmapDrawable(bitmap)
                    }

                    override fun onFailure(exception: Exception) {
                        TODO("Not yet implemented")
                    }
                })
            }
        }
    }
}