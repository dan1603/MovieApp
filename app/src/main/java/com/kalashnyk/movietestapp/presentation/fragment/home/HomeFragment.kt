package com.kalashnyk.movietestapp.presentation.fragment.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.kalashnyk.movietestapp.R
import com.kalashnyk.movietestapp.databinding.FragmentHomeBinding
import com.kalashnyk.movietestapp.presentation.fragment.home.adapter.MovieAdapter
import com.kalashnyk.movietestapp.presentation.fragment.home.listener.MovieItemClickListener
import com.kalashnyk.movietestapp.util.ResultWrapper
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment(), MovieItemClickListener {

    private val viewModel: HomeViewModel by viewModel()

    private var movieAdapter = MovieAdapter(this)

    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupAdapterList()
        setupViewModel()
    }

    override fun onItemMovieClick(movieId: Int) {
        findNavController().navigate(
            R.id.detailFragment,
            Bundle().apply {
                putInt(KEY_BUNDLE_MOVIE_ID, movieId)
            }
        )
    }

    private fun setupAdapterList() = with(binding) {
        rvMovieList.layoutManager = LinearLayoutManager(requireContext())
        rvMovieList.adapter = movieAdapter
    }

    private fun setupViewModel() {
        lifecycleScope.launch {
            viewModel.popularMovies.collect {
                when (it.status) {
                    ResultWrapper.Status.SUCCESS -> it.data?.let(movieAdapter::setList)
                    ResultWrapper.Status.ERROR -> it.error?.message?.let(::showError)
                    else -> Unit
                }
            }
        }
    }

    private fun showError(error: String) = Toast.makeText(context, error, Toast.LENGTH_LONG).show()

    companion object {
        private const val KEY_BUNDLE_MOVIE_ID = "movieId"
    }
}
