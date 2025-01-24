package com.onespan.android.interview.breed
import androidx.paging.PagingData
import androidx.paging.map
import com.onespan.android.interview.features.cats.CatsViewModel
import com.onespan.android.interview.features.cats.data.CatsRepository
import com.onespan.android.interview.features.cats.data.GetCatsUseCase
import com.onespan.android.interview.features.cats.model.Breed
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class BreedsViewModelTest {

    @Mock
    private lateinit var repository: CatsRepository

    private lateinit var viewModel: CatsViewModel

    @Before
    fun setUp() {
        // Mock repository's getBreedsPagingData to return a flow of PagingData
        `when`(repository.getPagedBreeds()).thenReturn(
            flowOf(
                PagingData.from(
                    listOf(
                        Breed("LaPerm", "United States", "Mutation", "Rex", "All"),
                        Breed("Maine Coon", "United States", "Natural", "Long", "All but colorpoint and ticked")
                    )
                )
            )
        )

        viewModel = CatsViewModel(
            getCatsUseCase = GetCatsUseCase(repository)
        )
    }

    @Test
    fun `should emit paginated data from repository`() = runTest {

        val collectedData = mutableListOf<Breed>()
        viewModel.breeds.collectLatest { data ->
            data.map {
                collectedData.add(it)
            }
        }

        verify(repository).getPagedBreeds()

        assertEquals(2, collectedData.size)
        assertEquals("LaPerm", collectedData[0].breed)
        assertEquals("Maine Coon", collectedData[1].breed)
    }
}
