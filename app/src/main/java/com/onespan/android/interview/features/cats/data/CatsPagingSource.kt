package com.onespan.android.interview.features.cats.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.onespan.android.interview.features.cats.api.CatService
import com.onespan.android.interview.features.cats.api.toDomainModel
import com.onespan.android.interview.features.cats.model.Breed
import kotlinx.coroutines.delay
import retrofit2.HttpException
import java.io.IOException

class CatsPagingSource(
    private val apiService: CatService
) : PagingSource<Int, Breed>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Breed> {
        val page = params.key ?: 1
        return try {
            delay(2000)
            val response = apiService.getBreeds(page)
            val breeds = response.data.map { it.toDomainModel() }
            LoadResult.Page(
                data = breeds,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (response.nextPageUrl == null) null else page + 1
            )
        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Breed>): Int? {
        return state.anchorPosition
    }
}
