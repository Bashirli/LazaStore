package com.bashirli.lazastore.domain.repo

import com.bashirli.lazastore.common.util.Resource
import com.bashirli.lazastore.data.dto.AuthDTO
import com.bashirli.lazastore.data.dto.ProductDTOItem
import com.bashirli.lazastore.domain.model.AuthModel
import com.bashirli.lazastore.domain.model.ProductModel
import com.bashirli.lazastore.domain.model.RegisterModel
import com.bashirli.lazastore.domain.model.RegisterPostModel
import kotlinx.coroutines.flow.Flow

interface ApiRepository {

    suspend fun loginUser(email:String,password:String): Flow<Resource<AuthModel>>

    suspend fun registerUser(registerPostModel: RegisterPostModel):Flow<Resource<RegisterModel>>

    suspend fun getProducts():Flow<Resource<List<ProductModel>>>

}