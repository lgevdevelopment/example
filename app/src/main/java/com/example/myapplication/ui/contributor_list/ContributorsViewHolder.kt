package com.example.myapplication.ui.contributor_list

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.api.results.Contributor

class ContributorsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val contributorIcon: ImageView = itemView.findViewById(R.id.contributor_avatar)
    val contributorId: TextView = itemView.findViewById(R.id.contributor_id)
    val contributorName: TextView = itemView.findViewById(R.id.contributor_name)

    fun bind(contributor: Contributor, contributorItemEnabled: Any, function: () -> Unit?) {
        contributorId.text = contributor.id
        contributorName.text = contributor.login
    }
}