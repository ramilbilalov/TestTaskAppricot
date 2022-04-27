package com.bilalov.testapplicationforappricot.request

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ResponseListInfo(
    var count: String,
    var pages: String,
    var next: String,
    var prev: String
) : Parcelable
