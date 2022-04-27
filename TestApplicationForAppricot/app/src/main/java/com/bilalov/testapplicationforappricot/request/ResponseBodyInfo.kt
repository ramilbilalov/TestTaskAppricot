package com.bilalov.testapplicationforappricot.request

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ResponseBodyInfo(
    var info: ResponseListInfo,
    var results: List<ResponseListProfile>,
) : Parcelable
