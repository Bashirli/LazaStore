package com.bashirli.lazastore.data.service

import com.bashirli.lazastore.data.dto.AuthDTO
import com.bashirli.lazastore.data.dto.CategoryDTO
import com.bashirli.lazastore.data.dto.product.ProductDTO
import com.bashirli.lazastore.data.dto.SingleProductDTO
import com.bashirli.lazastore.data.dto.cart.CartDTO
import com.bashirli.lazastore.data.dto.cart.CartUpdateDTO
import com.bashirli.lazastore.data.dto.register.RegisterDTO
import com.bashirli.lazastore.data.dto.search.SearchDTO
import com.bashirli.lazastore.data.dto.user.UserDTO
import com.bashirli.lazastore.domain.model.RegisterPostModel
import com.bashirli.lazastore.domain.model.body.UpdateCartBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface Service {

    @POST("auth/login")
    @FormUrlEncoded
    suspend fun loginUser(@Field("username") username:String,@Field("password") password:String):Response<AuthDTO>

    @POST("users/add")
    suspend fun registerUser(@Body registerPostModel: RegisterPostModel):Response<RegisterDTO>

    @GET("users/{id}")
    suspend fun getCurrentUser(@Path("id") id:Int=1):Response<UserDTO>

    @GET("products")
    suspend fun getProducts():Response<ProductDTO>

    @GET("products/categories")
    suspend fun getCategories():Response<CategoryDTO>

    @GET("products/category/{category}")
    suspend fun getCategoryProducts(@Path("category") category:String):Response<ProductDTO>

    @GET("products/{id}")
    suspend fun getSingleProduct(@Path("id") id:Int):Response<SingleProductDTO>

    @GET("carts/user/{id}")
    suspend fun getCurrentUserCart(@Path("id") id:Int=1):Response<CartDTO>

    @PUT("carts/{id}")
    suspend fun updateUserCart(@Body updateCartBody: UpdateCartBody, @Path("id") id:Int):Response<CartUpdateDTO>

    @GET("products/search")
    suspend fun getSearchProducts(@Query("q") searchText:String) : Response<SearchDTO>

}