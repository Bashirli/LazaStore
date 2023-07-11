package com.bashirli.lazastore.data.source

import com.bashirli.lazastore.common.util.Resource
import com.bashirli.lazastore.data.dto.remote.AuthDTO
import com.bashirli.lazastore.data.dto.remote.CategoryDTO
import com.bashirli.lazastore.data.dto.remote.product.ProductDTO
import com.bashirli.lazastore.data.dto.remote.SingleProductDTO
import com.bashirli.lazastore.data.dto.remote.cart.CartDTO
import com.bashirli.lazastore.data.dto.remote.cart.CartUpdateDTO
import com.bashirli.lazastore.data.dto.remote.register.RegisterDTO
import com.bashirli.lazastore.data.dto.remote.search.SearchDTO
import com.bashirli.lazastore.data.dto.remote.user.UserDTO
import com.bashirli.lazastore.domain.model.remote.RegisterPostModel
import com.bashirli.lazastore.domain.model.remote.body.UpdateCartBody

interface ApiSource {

    suspend fun loginUser(username:String,password:String): Resource<AuthDTO>

    suspend fun registerUser(registerPostModel: RegisterPostModel):Resource<RegisterDTO>

    suspend fun getProducts():Resource<ProductDTO>

    suspend fun getCategories():Resource<CategoryDTO>

    suspend fun getCurrentUser():Resource<UserDTO>

    suspend fun getCurrentProfile():Resource<UserDTO>

    suspend fun getCategoryProducts(category:String):Resource<ProductDTO>

    suspend fun getSingleProduct(id:Int): Resource<SingleProductDTO>

    suspend fun getCurrentUserCart(): Resource<CartDTO>

    suspend fun updateUserCart(updateCartBody: UpdateCartBody, cartId:Int):Resource<CartUpdateDTO>

    suspend fun getSearchProducts(searchText:String) : Resource<SearchDTO>
}