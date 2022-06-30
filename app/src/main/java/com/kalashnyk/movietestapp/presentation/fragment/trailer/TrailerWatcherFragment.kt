package com.kalashnyk.movietestapp.presentation.fragment.trailer

import android.net.http.SslError
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.kalashnyk.movietestapp.databinding.FragmentTrailerWatcherBinding
import com.kalashnyk.movietestapp.util.Constants

class TrailerWatcherFragment : Fragment() {

    private val args: TrailerWatcherFragmentArgs by navArgs()
    private val videoKey: String by lazy { args.videoKey }

    private lateinit var binding: FragmentTrailerWatcherBinding

    private val appWebViewClient = object : WebViewClient() {

        override fun onReceivedError(
            view: WebView?,
            request: WebResourceRequest?,
            error: WebResourceError?
        ) {
            super.onReceivedError(view, request, error)
            error?.description?.toString()?.let(::showError)
        }

        override fun onReceivedHttpError(
            view: WebView?,
            request: WebResourceRequest?,
            errorResponse: WebResourceResponse?
        ) {
            super.onReceivedHttpError(view, request, errorResponse)
            val error = errorResponse?.toString() ?: Constants.Error.ERROR_UNKNOWN
            showError(error)
        }

        override fun onReceivedSslError(
            view: WebView?,
            handler: SslErrorHandler?,
            error: SslError?
        ) {
            super.onReceivedSslError(view, handler, error)
            val sslError = error?.primaryError?.toString() ?: Constants.Error.ERROR_UNKNOWN
            showError(sslError)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTrailerWatcherBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnBack.setOnClickListener { findNavController().navigateUp() }
        binding.webView.apply {
            val youtubeLink = Constants.Url.YOUTUBE_URL + videoKey
            settings.javaScriptEnabled = true
            webViewClient = appWebViewClient
            loadUrl(youtubeLink)
        }
    }

    private fun showError(error: String) = Toast.makeText(context, error, Toast.LENGTH_LONG).show()
}
