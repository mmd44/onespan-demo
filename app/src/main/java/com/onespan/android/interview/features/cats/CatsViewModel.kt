package com.onespan.android.interview.features.cats

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.onespan.android.interview.features.cats.data.GetCatsUseCase
import com.onespan.android.interview.features.cats.model.Breed
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


@HiltViewModel
class CatsViewModel @Inject constructor(
    getCatsUseCase: GetCatsUseCase
) : ViewModel() {
    val breeds: Flow<PagingData<Breed>> = getCatsUseCase().cachedIn(viewModelScope)
}
