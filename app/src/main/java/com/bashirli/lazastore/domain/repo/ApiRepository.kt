package com.bashirli.lazastore.domain.repo

import com.bashirli.lazastore.common.util.Resource
import com.bashirli.lazastore.domain.model.remote.AuthModel
import com.bashirli.lazastore.domain.model.remote.CategoryModel
import com.bashirli.lazastore.domain.model.remote.MainProductModel
import com.bashirli.lazastore.domain.model.remote.ProfileModel
import com.bashirli.lazastore.domain.model.remote.RegisterModel
import com.bashirli.lazastore.domain.model.remote.RegisterPostModel
import com.bashirli.lazastore.domain.model.remote.SingleProductModel
import com.bashirli.lazastore.domain.model.remote.UserModel
import com.bashirli.lazastore.domain.model.remote.body.UpdateCartBody
import com.bashirli.lazastore.domain.model.remote.cart.CartMainModel
import com.bashirli.lazastore.domain.model.remote.cart.CartModel
import kotlinx.coroutines.flow.Flow

interface ApiRepository {

    suspend fun loginUser(username:String,password:String): Flow<Resource<AuthModel>>

    suspend fun registerUser(registerPostModel: RegisterPostModel):Flow<Resource<RegisterModel>>

    suspend fun getCurrentUser():Flow<Resource<UserModel>>

    suspend fun getCurrentProfile():Flow<Resource<ProfileModel>>

    suspend fun getProducts():Flow<Resource<MainProductModel>>

    suspend fun getCategories():Flow<Resource<List<CategoryModel>>>

    suspend fun getCategoryProducts(category:String):Flow<Resource<MainProductModel>>

    suspend fun getSingleProduct(id:Int):Flow<Resource<SingleProductModel>>

    suspend fun getCurrentUserCart(): Flow<Resource<CartMainModel>>

    suspend fun updateUserCart(updateCartBody: UpdateCartBody, cartId:Int):Flow<Resource<CartModel>>

    suspend fun getSearchProducts(searchText:String):Flow<Resource<MainProductModel>>
}