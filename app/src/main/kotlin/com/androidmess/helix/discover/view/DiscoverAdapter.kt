package com.androidmess.helix.discover.view;

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.androidmess.helix.R
import com.androidmess.helix.common.ui.inflate
import com.androidmess.helix.common.ui.load
import com.androidmess.helix.discover.model.data.DiscoverMovieViewModel
import kotlinx.android.synthetic.main.discover_list_item.view.*

class DiscoverAdapter : RecyclerView.Adapter<DiscoverAdapter.ViewHolder>() {

    private val data: MutableList<DiscoverMovieViewModel> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder
            = ViewHolder(parent.inflate(R.layout.discover_list_item, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int)
            = holder.bind(data[position])

    override fun getItemCount(): Int
            = data.size

    fun addData(movies: List<DiscoverMovieViewModel>) {
        data.addAll(movies)
        notifyDataSetChanged()
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val title: TextView = itemView.discoverItemText
        private val image: ImageView = itemView.discoverItemImage

        fun bind(movieModel: DiscoverMovieViewModel) {
            title.text = movieModel.title
            image.load(path = movieModel.imagePath)
        }
    }
}