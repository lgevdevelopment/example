package com.example.myapplication.ui.contributor_list

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.api.listeners.IObtainImageListener
import com.example.myapplication.api.results.Contributor
import com.example.myapplication.image_repository.ImageRepository

class ContributorsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val contributorIcon: View = itemView.findViewById(R.id.contributor_avatar)
    val contributorId: TextView = itemView.findViewById(R.id.contributor_id)
    val contributorName: TextView = itemView.findViewById(R.id.contributor_name)

    fun bind(contributor: Contributor) {
        ImageRepository.obtainImage(contributor.avatar_url, contributorIcon.width, contributorIcon.height, object : IObtainImageListener {
            override fun onObtainImage(bitmap: Bitmap) {
                contributorIcon.background = BitmapDrawable(bitmap)
            }

            override fun onFailure(exception: Exception) {
                TODO("Not yet implemented")
            }

        })
        contributorId.text = contributor.id
        contributorName.text = contributor.login
    }
}