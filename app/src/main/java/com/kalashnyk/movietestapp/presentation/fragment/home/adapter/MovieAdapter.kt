package com.kalashnyk.movietestapp.presentation.fragment.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kalashnyk.movietestapp.databinding.ItemMovieBinding
import com.kalashnyk.movietestapp.network.model.MovieItemResponse
import com.kalashnyk.movietestapp.network.model.MovieListResponse
import com.kalashnyk.movietestapp.presentation.fragment.home.listener.MovieItemClickListener

class MovieAdapter(
    private val listener: MovieItemClickListener,
) : RecyclerView.Adapter<MovieViewHolder>() {

    private var movieList = mutableListOf<MovieItemResponse>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MovieViewHolder(
        ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false),
        listener
    )

    override fun getItemCount(): Int = movieList.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(movieList[position])
    }

    fun setList(movies: MovieListResponse) {
        movieList.clear()
        movieList.addAll(movies.result)
        notifyDataSetChanged()
    }
}
