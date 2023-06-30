package com.bashirli.lazastore.data.source

import com.bashirli.lazastore.common.util.Resource
import com.bashirli.lazastore.data.dto.AuthDTO
import com.bashirli.lazastore.data.dto.CategoryDTO
import com.bashirli.lazastore.data.dto.Product
import com.bashirli.lazastore.data.dto.ProductDTO
import com.bashirli.lazastore.data.dto.SingleProductDTO
import com.bashirli.lazastore.data.dto.register.RegisterDTO
import com.bashirli.lazastore.data.dto.user.UserDTO
import com.bashirli.lazastore.domain.model.RegisterPostModel

interface ApiSource {

    suspend fun loginUser(username:String,password:String): Resource<AuthDTO>

    suspend fun registerUser(registerPostModel: RegisterPostModel):Resource<RegisterDTO>

    suspend fun getProducts():Resource<ProductDTO>

    suspend fun getCategories():Resource<CategoryDTO>

    suspend fun getCurrentUser():Resource<UserDTO>

    suspend fun getCurrentProfile():Resource<UserDTO>

    suspend fun getCategoryProducts(category:String):Resource<ProductDTO>

    suspend fun getSingleProduct(id:Int): Resource<SingleProductDTO>


}