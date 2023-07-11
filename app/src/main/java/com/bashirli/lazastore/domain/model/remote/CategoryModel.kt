package com.bashirli.lazastore.domain.model.remote

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CategoryModel(
    val category:String
):Parcelable