package com.bashirli.lazastore.domain.repo

import com.bashirli.lazastore.common.util.Resource
import com.bashirli.lazastore.domain.model.AuthModel
import com.bashirli.lazastore.domain.model.CategoryModel
import com.bashirli.lazastore.domain.model.MainProductModel
import com.bashirli.lazastore.domain.model.ProductModel
import com.bashirli.lazastore.domain.model.ProfileModel
import com.bashirli.lazastore.domain.model.RegisterModel
import com.bashirli.lazastore.domain.model.RegisterPostModel
import com.bashirli.lazastore.domain.model.SingleProductModel
import com.bashirli.lazastore.domain.model.UserModel
import kotlinx.coroutines.flow.Flow

interface ApiRepository {

    suspend fun loginUser(email:String,password:String): Flow<Resource<AuthModel>>

    suspend fun registerUser(registerPostModel: RegisterPostModel):Flow<Resource<RegisterModel>>

    suspend fun getCurrentUser():Flow<Resource<UserModel>>

    suspend fun getCurrentProfile():Flow<Resource<ProfileModel>>

    suspend fun getProducts():Flow<Resource<MainProductModel>>

    suspend fun getCategories():Flow<Resource<List<CategoryModel>>>

    suspend fun getCategoryProducts(category:String):Flow<Resource<MainProductModel>>

    suspend fun getSingleProduct(id:Int):Flow<Resource<SingleProductModel>>

}