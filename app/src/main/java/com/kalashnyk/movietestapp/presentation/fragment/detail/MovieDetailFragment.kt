package com.kalashnyk.movietestapp.presentation.fragment.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.kalashnyk.movietestapp.R
import com.kalashnyk.movietestapp.databinding.FragmentDetailBinding
import com.kalashnyk.movietestapp.network.model.MovieItemResponse
import com.kalashnyk.movietestapp.util.Constants
import com.kalashnyk.movietestapp.util.ResultWrapper
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MovieDetailFragment : Fragment() {

    private val viewModel: MovieViewModel by viewModel()

    private val args: MovieDetailFragmentArgs by navArgs()
    private val movieId: Int by lazy { args.movieId }

    private lateinit var binding: FragmentDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnBack.setOnClickListener { findNavController().navigateUp() }
        movieId.let(viewModel::fetchMovieById)
        movieId.let(viewModel::fetchMovieTrailer)
        setupViewModel()
    }

    private fun setupViewModel() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.movieDetails.collect { uiState ->
                when (uiState.status) {
                    ResultWrapper.Status.SUCCESS -> uiState.data?.let(::showDetail)
                    ResultWrapper.Status.ERROR -> uiState.error?.message?.let(::showError)
                    else -> Unit
                }
            }
        }
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.lastTrailer.collect { uiState ->
                when (uiState.status) {
                    ResultWrapper.Status.SUCCESS -> uiState.data?.let { trailer ->
                        binding.btnOpenTrailer.setOnClickListener {
                            findNavController().navigate(
                                R.id.trailerWatcherFragment,
                                Bundle().apply {
                                    putString(KEY_BUNDLE_VIDEO, trailer.keyUrl)
                                }
                            )
                        }
                    }
                    ResultWrapper.Status.ERROR -> uiState.error?.message?.let { showError(it) }
                    else -> Unit
                }
            }
        }
    }

    private fun showDetail(movie: MovieItemResponse) = with(binding) {
        tvTitle.text = movie.title
        tvDescription.text = movie.overview
        tvVoteAverage.text = movie.voteAverage.toString()
        Glide.with(this@MovieDetailFragment)
            .load(Constants.Base.IMAGE_BASE + movie.poster)
            .into(ivPoster)
    }

    private fun showError(error: String) = Toast.makeText(context, error, Toast.LENGTH_LONG).show()

    companion object {
        private const val KEY_BUNDLE_VIDEO = "videoKey"
    }
}
