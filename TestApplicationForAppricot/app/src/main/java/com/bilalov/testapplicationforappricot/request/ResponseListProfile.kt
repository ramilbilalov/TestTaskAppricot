package com.bilalov.testapplicationforappricot.request

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ResponseListProfile(
    val name: String,
    val status: String,
    val species: String,
    val gender: String,
    val image: String,
    val location: ResponseLocationProfile,
    val episode: List<String>
) : Parcelable