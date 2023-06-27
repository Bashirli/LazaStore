package com.bashirli.lazastore.domain.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CategoryModel(
    val id: Int,
    val image: String,
    val name: String
):Parcelable