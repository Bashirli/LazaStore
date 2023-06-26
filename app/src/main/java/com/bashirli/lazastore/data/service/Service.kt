package com.bashirli.lazastore.data.service

import com.bashirli.lazastore.data.dto.AuthDTO
import com.bashirli.lazastore.data.dto.CategoryDTOItem
import com.bashirli.lazastore.data.dto.ProductDTOItem
import com.bashirli.lazastore.data.dto.RegisterDTO
import com.bashirli.lazastore.data.dto.UserDTO
import com.bashirli.lazastore.domain.model.RegisterPostModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface Service {

    @POST("auth/login")
    @FormUrlEncoded
    suspend fun loginUser(@Field("email") email:String,@Field("password") password:String):Response<AuthDTO>

    @POST("users")
    suspend fun registerUser(@Body registerPostModel: RegisterPostModel):Response<RegisterDTO>

    @GET("auth/profile")
    suspend fun getCurrentUser():Response<UserDTO>

    @GET("products")
    suspend fun getProducts():Response<List<ProductDTOItem>>

    @GET("categories")
    suspend fun getCategories():Response<List<CategoryDTOItem>>

}