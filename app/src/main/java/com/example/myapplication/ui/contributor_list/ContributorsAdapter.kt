package com.example.myapplication.ui.contributor_list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.api.results.Contributor

class ContributorsAdapter : ListAdapter<Contributor, RecyclerView.ViewHolder>(DataItemDiffCallback) {
    var onClick: ((Contributor) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        ContributorsViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.contributor_list_item, parent, false)
        )


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ContributorsViewHolder -> {
                val contributor = getItem(position) as Contributor
                holder.bind(contributor)
            }
        }
    }

    object DataItemDiffCallback : DiffUtil.ItemCallback<Contributor>() {
        override fun areItemsTheSame(oldItem: Contributor, newItem: Contributor) = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: Contributor, newItem: Contributor) = oldItem.login == newItem.login && oldItem.id == newItem.id
    }
}