package com.example.movie.features.feed.presentation.input

interface IFeedViewModelInput {
    fun openDetail(movieName : String)
    fun openInfoDialog()
    fun refreshFeed():Int?{
        return null
    }
}

