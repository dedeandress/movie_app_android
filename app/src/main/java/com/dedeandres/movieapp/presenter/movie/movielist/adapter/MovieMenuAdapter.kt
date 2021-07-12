package com.dedeandres.movieapp.presenter.movie.movielist.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dedeandres.movieapp.R
import com.dedeandres.movieapp.databinding.ItemMovieMenuBinding
import timber.log.Timber

class MovieMenuAdapter : RecyclerView.Adapter<MovieMenuAdapter.ViewHolder>() {

    private val items: ArrayList<String> = ArrayList()
    private var onItemClickListener: OnItemClickListener? = null
    var checkedPosition = 0

    init {
        Timber.d("checked position: $checkedPosition")
    }

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener) {
        this.onItemClickListener = onItemClickListener
    }

    fun bind(items: List<String>) {
        this.items.clear()
        if (this.items.isEmpty()) {
            this.items.addAll(items)
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding =
            ItemMovieMenuBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.bindMovieMenu(item)
    }

    override fun getItemCount(): Int = items.size

    interface OnItemClickListener {
        fun onMovieMenuClick(movieMenu: String)
    }


    inner class ViewHolder(private val itemBinding: ItemMovieMenuBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {

        fun bindMovieMenu(movieMenu: String) {
            itemBinding.tvMovieMenu.text = movieMenu

            if (checkedPosition == adapterPosition) {
                itemBinding.vLine.visibility = View.VISIBLE
                itemBinding.tvMovieMenu.setTextColor(itemView.resources.getColor(R.color.black))
            } else {
                itemBinding.vLine.visibility = View.GONE
                itemBinding.tvMovieMenu.setTextColor(itemView.resources.getColor(R.color.greyish))
            }

            itemView.setOnClickListener {
                itemBinding.vLine.visibility = View.VISIBLE
                itemBinding.tvMovieMenu.setTextColor(itemView.resources.getColor(R.color.black))
                onItemClickListener?.onMovieMenuClick(movieMenu)
                if (checkedPosition != adapterPosition) {
                    notifyItemChanged(checkedPosition)
                    checkedPosition = adapterPosition
                    Timber.d("checked position: $checkedPosition")
                }
            }
        }
    }
}