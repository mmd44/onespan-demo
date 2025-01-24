package com.onespan.android.interview.features.cats.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.onespan.android.interview.features.cats.api.CatService
import com.onespan.android.interview.features.cats.model.Breed
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CatsRepository @Inject constructor(
    private val apiService: CatService
) {
    fun getPagedBreeds(): Flow<PagingData<Breed>> {
        return Pager(
            config = PagingConfig(
                pageSize = 25,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { CatsPagingSource(apiService) }
        ).flow
    }
}
