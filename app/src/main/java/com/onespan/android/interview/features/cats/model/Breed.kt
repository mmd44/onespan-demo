package com.onespan.android.interview.features.cats.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Breed (
    val breed: String,
    val country: String,
    val origin: String,
    val coat: String,
    val pattern: String
): Parcelable
