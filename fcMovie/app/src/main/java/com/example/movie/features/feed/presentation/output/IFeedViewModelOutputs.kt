package com.example.movie.features.feed.presentation.output

import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow

interface IFeedViewModelOutputs {
    val feedState : StateFlow<FeedState>
    val feedUiEffect : SharedFlow<FeedUiEffect>
}

sealed class FeedUiEffect{
    data class OpenMovieDetail(val movieName : String) :  FeedUiEffect()
    object OpenInfoDialog : FeedUiEffect()

}