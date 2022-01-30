package com.dev.ui.paging

import androidx.lifecycle.asLiveData
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.dev.core.models.Children
import com.dev.core.repository.PostsRepository
import java.lang.Exception

class PostDataSource(val repository: PostsRepository):PagingSource<Int,Children>() {
    override fun getRefreshKey(state: PagingState<Int, Children>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Children> {
        val pageNumber = params.key ?: 1
        try {

            val data = repository.getRedditPosts().asLiveData().value?.data?.data?.children
            return LoadResult.Page(
                data = data.orEmpty(),
                prevKey = null,
                nextKey = pageNumber+1
            )
        }catch (e:Exception){
            return LoadResult.Error(e)
        }

    }

}