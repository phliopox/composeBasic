package com.example.movie.features.feed

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.ComposeView
import androidx.navigation.fragment.findNavController
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.movie.features.feed.presentation.output.FeedUiEffect
import com.example.movie.features.feed.presentation.viewmodel.FeedViewModel
import com.example.movie.features.feed.screen.FeedScreen
import com.example.movie.ui.BaseFragment
import com.example.movie.ui.navigation.safeNavigate
import com.example.movie.ui.theme.MovieTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FeedFragment : BaseFragment(){

    private val viewModel : FeedViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent{
                MovieTheme(
                    themeState = themeViewModel.themeState.collectAsState()
                ) {
                    FeedScreen(
                        feedStateHolder = viewModel.output.feedState.collectAsState(),
                        input = viewModel.input,
                        buttonColor = themeViewModel.nextColorState.collectAsState(),
                        changeAppColor = { themeViewModel.toggleColorSet() }
                    )
                }
            }
        }
    }
    private fun observeUiEffects() {
        val navController = findNavController()
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.output.feedUiEffect.collectLatest {
                    when (it) {
                        is FeedUiEffect.OpenMovieDetail -> {
                            navController.safeNavigate(
                                FeedFragmentDirections.actionFeedToDetail(it.movieName)
                            )
                        }

                        is FeedUiEffect.OpenInfoDialog -> {
                            /*navController.safeNavigate(
                                FeedFragmentDirections.actionFeedToInfo()
                            )*/
                        }
                    }
                }
            }
        }
    }
}