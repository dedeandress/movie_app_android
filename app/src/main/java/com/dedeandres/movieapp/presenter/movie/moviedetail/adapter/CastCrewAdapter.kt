package com.dedeandres.movieapp.presenter.movie.moviedetail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dedeandres.movieapp.R
import com.dedeandres.movieapp.databinding.ItemCastCrewBinding
import com.dedeandres.movieapp.presenter.movie.moviedetail.entity.CastCrewResult
import com.dedeandres.movieapp.presenter.movie.movielist.entity.MovieResult
import timber.log.Timber

class CastCrewAdapter : RecyclerView.Adapter<CastCrewAdapter.ViewHolder>() {

    private val items: ArrayList<CastCrewResult> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding =
            ItemCastCrewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemBinding)
    }

    fun bind(items: List<CastCrewResult>) {
        this.items.clear()
        if (this.items.isEmpty()) {
            this.items.addAll(items)
            Timber.d("CastCrewAdapter bind: ${this.items}")
        }
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.bindCastCrew(item)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class ViewHolder(private val itemCastCrewBinding: ItemCastCrewBinding) :
        RecyclerView.ViewHolder(itemCastCrewBinding.root) {

        fun bindCastCrew(castCrewResult: CastCrewResult) {
            itemCastCrewBinding.tvKnownAs.text = castCrewResult.knownForDepartment
            itemCastCrewBinding.tvName.text = castCrewResult.name
            Glide.with(itemCastCrewBinding.root)
                .load("https://image.tmdb.org/t/p/original" + castCrewResult.posterPath)
                .placeholder(R.drawable.ic_profile)
                .error(R.drawable.ic_profile)
                .into(itemCastCrewBinding.civProfile)
        }
    }
}