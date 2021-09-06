package com.faraji.socialnetwork.presentation.postDetail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PostDetailViewModel @Inject constructor() : ViewModel() {
    private val _commentText = mutableStateOf("")
    val commentText: State<String> = _commentText

    fun setCommentText(comment: String) {
        _commentText.value = comment
    }
}