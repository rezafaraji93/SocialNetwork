package com.faraji.socialnetwork.feature_post.data.remote.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.faraji.socialnetwork.core.domain.models.Post
import com.faraji.socialnetwork.core.util.Constants
import com.faraji.socialnetwork.feature_post.data.remote.PostApi
import okio.IOException
import retrofit2.HttpException

class PostSource(
    private val api: PostApi
) : PagingSource<Int, Post>() {

    private var currentPage = 0

    override fun getRefreshKey(state: PagingState<Int, Post>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Post> {
        return try {
            val nextPage = params.key ?: currentPage
            val posts = api.getPostsForFollows(
                page = nextPage,
                pageSize = Constants.PAGE_SIZE_POSTS
            )
            LoadResult.Page(
                data = posts,
                prevKey = if (nextPage == 0) null else nextPage - 1,
                nextKey = if (posts.isEmpty()) null else currentPage + 1

            ).also {
                currentPage++
            }


        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }
    }
}