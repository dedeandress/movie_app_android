package com.dedeandres.movieapp.presenter.movie.movielist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dedeandres.movieapp.databinding.ItemMoviePosterBinding
import com.dedeandres.movieapp.presenter.movie.movielist.entity.MovieResult
import timber.log.Timber

class MovieListAdapter : RecyclerView.Adapter<MovieListAdapter.ViewHolder>() {

    private val items: ArrayList<MovieResult> = ArrayList()
    private var onItemClickListener: OnItemClickListener? = null

    init {
        Timber.d("MovieListAdapter init")
    }
    fun setOnItemClickListener(onItemClickListener: OnItemClickListener) {
        this.onItemClickListener = onItemClickListener
    }

    fun bind(items: List<MovieResult>) {
        this.items.clear()
        if (this.items.isEmpty()) {
            this.items.addAll(items)
            Timber.d("MovieListAdapter bind: ${this.items}")
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding =
            ItemMoviePosterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.bindMovie(item)
    }

    override fun getItemCount(): Int = items.size

    interface OnItemClickListener {
        fun onMovieItemClick(movieMenu: String)
    }


    inner class ViewHolder(private val itemBinding: ItemMoviePosterBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {

        fun bindMovie(movieMenu: MovieResult) {
            itemBinding.tvMovieTitle.text = movieMenu.originalTitle
            itemBinding.tvRating.text = movieMenu.voteAverage

            Glide.with(itemBinding.root)
                .load("https://image.tmdb.org/t/p/original" + movieMenu.posterPath)
                .into(itemBinding.ivPoster)
        }
    }
}