package com.faraji.socialnetwork.feature_post.presentation.main_feed

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.faraji.socialnetwork.feature_post.data.remote.PostApi
import com.faraji.socialnetwork.feature_post.domain.use_case.PostUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainFeedViewModel @Inject constructor(
    private val useCase: PostUseCases,
    private val api: PostApi
) : ViewModel() {

    private val _state = mutableStateOf(MainFeedState())
    val state: State<MainFeedState> = _state

    val posts = useCase.getPostForFollowsUseCase()
        .cachedIn(viewModelScope)

    fun onEvent(event: MainFeedEvent) {
        when (event) {
            is MainFeedEvent.LoadMorePosts -> {
                _state.value = state.value.copy(
                    isLoadingNewPosts = true
                )
            }
            is MainFeedEvent.LoadedPage -> {
                _state.value = state.value.copy(
                    isLoadingNewPosts = false,
                    isLoadingFirstTime = false
                )
            }
        }
    }
}