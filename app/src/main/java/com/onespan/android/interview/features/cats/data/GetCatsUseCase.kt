package com.onespan.android.interview.features.cats.data

import androidx.paging.PagingData
import com.onespan.android.interview.features.cats.model.Breed
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class GetCatsUseCase @Inject constructor(
    private val repository: CatsRepository
) {
    operator fun invoke(): Flow<PagingData<Breed>> = repository.getPagedBreeds()
}