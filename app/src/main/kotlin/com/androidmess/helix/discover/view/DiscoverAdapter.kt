package com.androidmess.helix.discover.view

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil.ItemCallback
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.androidmess.helix.R
import com.androidmess.helix.common.navigation.Navigator
import com.androidmess.helix.common.ui.view.inflate
import com.androidmess.helix.core.data.models.Movie
import com.androidmess.helix.databinding.DiscoverListItemBinding

class DiscoverAdapter(
    private val navigator: Navigator
) : ListAdapter<Movie, DiscoverAdapter.ViewHolder>(DIFF_UTIL) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = parent.inflate(R.layout.discover_list_item, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    fun onMovieClick(view: View, movie: Movie) {
        navigator.onMovieClick(view, movie)
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private var binding: DiscoverListItemBinding = DiscoverListItemBinding.bind(view)

        fun bind(movieData: Movie) {
            binding.movie = movieData
            binding.adapter = this@DiscoverAdapter
        }
    }

    companion object {
        val DIFF_UTIL = object : ItemCallback<Movie>() {
            override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(
                oldItem: Movie,
                newItem: Movie
            ): Boolean = oldItem.id == newItem.id
        }
    }
}
