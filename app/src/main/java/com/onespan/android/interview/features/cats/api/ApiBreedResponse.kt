package com.onespan.android.interview.features.cats.api

import android.os.Parcelable
import com.onespan.android.interview.features.cats.model.Breed
import kotlinx.parcelize.Parcelize


@Parcelize
data class ApiBreedResponse(
    val currentPage: Int,
    val lastPage: Int,
    val data: List<ApiBreed>,
    val nextPageUrl: String?,
    val path: String,
    val total: Int
): Parcelable

@Parcelize
data class ApiBreed(
    val breed: String,
    val country: String,
    val origin: String?,
    val coat: String?,
    val pattern: String?
): Parcelable

fun ApiBreed.toDomainModel(): Breed {
    return Breed(
        breed = this.breed,
        country = this.country,
        origin = this.origin ?: "",
        coat = this.coat ?: "",
        pattern = this.pattern ?: ""
    )
}
