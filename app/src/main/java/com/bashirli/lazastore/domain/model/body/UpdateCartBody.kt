package com.bashirli.lazastore.domain.model.body

import com.google.gson.annotations.SerializedName

data class UpdateCartBody(
    @SerializedName("merge")
    val merge:Boolean,
    @SerializedName("products")
    val updateProduct:List<UpdateProduct>
)