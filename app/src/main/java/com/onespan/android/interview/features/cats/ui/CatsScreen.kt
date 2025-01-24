package com.onespan.android.interview.features.cats.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.onespan.android.interview.R
import com.onespan.android.interview.base.snackbar.SnackbarManager
import com.onespan.android.interview.features.cats.CatsViewModel
import com.onespan.android.interview.features.cats.model.Breed
import kotlinx.coroutines.flow.MutableStateFlow


@Composable
fun CatsScreen(
    modifier: Modifier = Modifier,
    viewModel: CatsViewModel = hiltViewModel()
) {
    val breeds = viewModel.breeds.collectAsLazyPagingItems()

    BreedsScreenContent(
        modifier = modifier,
        breeds = breeds
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun BreedsScreenContent(
    modifier: Modifier = Modifier,
    breeds: LazyPagingItems<Breed>,
) {

    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
        modifier = modifier
            .fillMaxSize()
    ) {

        stickyHeader(key = "breed-title") {
            Row(
                Modifier.background(color = MaterialTheme.colorScheme.background)
                    .fillParentMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = stringResource(R.string.cat_breeds),
                    modifier = Modifier.padding(16.dp),
                )
            }
        }

        items(breeds.itemCount) { index ->
            breeds[index]?.let {
                CatElement(
                    breed = it.breed,
                    country = it.country.takeIf { country -> country.isNotEmpty() }
                        ?: stringResource(
                            R.string.unknown
                        ),
                    pattern = it.pattern,
                    coat = it.coat.takeIf { coat -> coat.isNotEmpty() } ?: stringResource(
                        R.string.no_coat
                    )
                )
            }
        }

        item {
            breeds.apply {
                when {
                    loadState.refresh is LoadState.Loading || loadState.append is LoadState.Loading -> {
                        CircularProgressIndicator(
                            modifier = Modifier.wrapContentSize().padding(12.dp)
                        )
                    }

                    loadState.append is LoadState.Error -> {
                        SnackbarManager.showMessage(
                            stringResource(
                                R.string.an_error_has_occurred
                            )
                        )
                    }
                }
            }
        }

    }
}

@Preview
@Composable
fun CatsScreenPreview() {
    val breeds: MutableStateFlow<PagingData<Breed>> = MutableStateFlow(
        PagingData.from(
            listOf(
                Breed(
                    breed = "Abyssinian",
                    origin = "Natural/Standard",
                    country = "Canada",
                    pattern = "All",
                    coat = "Short"
                )
            )
        )
    )
    BreedsScreenContent(
        breeds = breeds.collectAsLazyPagingItems()
    )
}