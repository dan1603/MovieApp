package com.kalashnyk.movietestapp.presentation.fragment.home.adapter

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kalashnyk.movietestapp.databinding.ItemMovieBinding
import com.kalashnyk.movietestapp.network.model.MovieItemResponse
import com.kalashnyk.movietestapp.presentation.fragment.home.listener.MovieItemClickListener
import com.kalashnyk.movietestapp.util.Constants

class MovieViewHolder(
    private val binding: ItemMovieBinding,
    private val listener: MovieItemClickListener,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: MovieItemResponse) = with(binding) {
        tvTitle.text = item.title

        Glide.with(binding.root)
            .load(Constants.Base.IMAGE_BASE + item.poster)
            .into(ivPoster)

        root.setOnClickListener {
            listener.onItemMovieClick(item.id)
        }
    }
}
