package com.saiferwp.squarerepos.ui.repos

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.saiferwp.squarerepos.R
import com.saiferwp.squarerepos.data.model.RepoData

class ReposListAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var items = mutableListOf<RepoData>()
    private var isLoaderVisible = false

    fun addData(
        items: List<RepoData>
    ) {
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    fun showLoading(show : Boolean) {
        isLoaderVisible = show
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            VIEW_TYPE_LOADING -> ProgressHolder(
                inflater.inflate(R.layout.list_item_loading, parent, false)
            )
            else -> RepoDataViewHolder(
                inflater.inflate(R.layout.list_item_repo, parent, false)
            )
        }
    }

    override fun getItemCount() = items.size + if (isLoaderVisible) 1 else 0

    override fun getItemViewType(position: Int): Int {
        return if (isLoaderVisible) {
            if (position == items.size) VIEW_TYPE_LOADING else VIEW_TYPE_NORMAL
        } else {
            VIEW_TYPE_NORMAL
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (!isLoaderVisible || (isLoaderVisible && position < items.size)) {
            (holder as RepoDataViewHolder).bind(items[position])
        }
    }

    class RepoDataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val name: TextView = itemView.findViewById(R.id.textView_repo_name)
        private val description: TextView = itemView.findViewById(R.id.textView_repo_description)
        private val language: TextView = itemView.findViewById(R.id.textView_repo_language)
        private val stars: TextView = itemView.findViewById(R.id.textView_repo_stars)
        private val archived: TextView = itemView.findViewById(R.id.textView_repo_archived)

        fun bind(
            repoData: RepoData
        ) {
            name.text = repoData.name
            description.text = repoData.description
            language.visibility = if (repoData.language.isNullOrEmpty()) View.GONE else View.VISIBLE
            language.text = repoData.language
            stars.text = String.format("%,d", repoData.stargazers_count)
            archived.visibility = if (repoData.archived) View.VISIBLE else View.GONE
        }
    }

    class ProgressHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    companion object {
        private const val VIEW_TYPE_LOADING = 0
        private const val VIEW_TYPE_NORMAL = 1
    }
}