package com.example.movie.features.feed.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movie.features.common.entity.EntityWrapper
import com.example.movie.features.feed.domain.usecase.IGetFeedCategoryUseCase
import com.example.movie.features.feed.presentation.input.IFeedViewModelInput
import com.example.movie.features.feed.presentation.output.FeedState
import com.example.movie.features.feed.presentation.output.FeedUiEffect
import com.example.movie.features.feed.presentation.output.IFeedViewModelOutputs
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FeedViewModel @Inject constructor(
    private val getFeedCategoryUseCase : IGetFeedCategoryUseCase
) : ViewModel(), IFeedViewModelOutputs, IFeedViewModelInput {

    val output: IFeedViewModelOutputs = this
    val input: IFeedViewModelInput = this
    //화면에 보여주기 위한 Flow

    private val _feedState: MutableStateFlow<FeedState> = MutableStateFlow(FeedState.Loading)
    override val feedState: StateFlow<FeedState> get() = _feedState

    //사용자 입력을 받기 위한 Flow
    private val _feedUiEffect = MutableSharedFlow<FeedUiEffect>(replay = 0)
    override val feedUiEffect: SharedFlow<FeedUiEffect>
        get() = _feedUiEffect

    init {
        fetchFeed()
    }
    private fun fetchFeed(){
        viewModelScope.launch {
            _feedState.value = FeedState.Loading
            val categories = getFeedCategoryUseCase()
            _feedState.value = when(categories){
                is EntityWrapper.Success ->{
                    FeedState.Main(categories.entity)
                }
                is EntityWrapper.Fail ->{
                    FeedState.Failed(categories.error.message?:"Unknown Error")
                }
            }
        }
    }

    override fun openDetail(movieName: String) {
        viewModelScope.launch {
        }
    }

    override fun openInfoDialog() {
        TODO("Not yet implemented")
    }

    override fun refreshFeed() {
        TODO("Not yet implemented")
    }
}