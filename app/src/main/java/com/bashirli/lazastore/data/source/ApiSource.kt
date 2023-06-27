package com.bashirli.lazastore.data.source

import com.bashirli.lazastore.common.util.Resource
import com.bashirli.lazastore.data.dto.AuthDTO
import com.bashirli.lazastore.data.dto.CategoryDTOItem
import com.bashirli.lazastore.data.dto.ProductDTOItem
import com.bashirli.lazastore.data.dto.RegisterDTO
import com.bashirli.lazastore.data.dto.SingleProductDTO
import com.bashirli.lazastore.data.dto.UserDTO
import com.bashirli.lazastore.domain.model.RegisterPostModel
import retrofit2.Response
import retrofit2.http.Path

interface ApiSource {

    suspend fun loginUser(email:String,password:String): Resource<AuthDTO>

    suspend fun registerUser(registerPostModel: RegisterPostModel):Resource<RegisterDTO>

    suspend fun getProducts():Resource<List<ProductDTOItem>>

    suspend fun getCategories():Resource<List<CategoryDTOItem>>

    suspend fun getCurrentUser():Resource<UserDTO>

    suspend fun getCategoryProducts(id:Int):Resource<List<ProductDTOItem>>

    suspend fun getSingleProduct(id:Int): Resource<SingleProductDTO>


}