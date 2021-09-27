package com.faraji.socialnetwork.core.util

typealias SimpleResource = Resource<Unit>

sealed class Resource<T>(data: T? = null, message: UiText? = null) {
    class Success<T>(data: T?) : Resource<T>(data)
    class Error<T>(message: UiText?, data: T? = null) : Resource<T>(data, message)
}

