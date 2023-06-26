package com.bashirli.lazastore.data.repo

import com.bashirli.lazastore.common.util.Resource
import com.bashirli.lazastore.common.util.Status
import com.bashirli.lazastore.data.dto.AuthDTO
import com.bashirli.lazastore.data.dto.ProductDTOItem
import com.bashirli.lazastore.data.mapper.toAuthModel
import com.bashirli.lazastore.data.mapper.toCategoryModel
import com.bashirli.lazastore.data.mapper.toProductModel
import com.bashirli.lazastore.data.mapper.toRegisterModel
import com.bashirli.lazastore.data.mapper.toUserModel
import com.bashirli.lazastore.data.source.ApiSource
import com.bashirli.lazastore.domain.model.AuthModel
import com.bashirli.lazastore.domain.model.CategoryModel
import com.bashirli.lazastore.domain.model.ProductModel
import com.bashirli.lazastore.domain.model.RegisterModel
import com.bashirli.lazastore.domain.model.RegisterPostModel
import com.bashirli.lazastore.domain.model.UserModel
import com.bashirli.lazastore.domain.repo.ApiRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ApiRepositoryImpl @Inject constructor(
        private val apiSource: ApiSource
) : ApiRepository {

    override suspend fun loginUser(email: String, password: String): Flow<Resource<AuthModel>>
    = flow{
        emit(Resource.loading(null))
        val response=apiSource.loginUser(email, password)
        when(response.status){
            Status.ERROR->{
                emit(Resource.error(response.message?:"Error",response.data?.toAuthModel()))
            }
            Status.SUCCESS->{
                emit(Resource.success(response.data?.toAuthModel()))
            }
            else->{}
        }
    }

    override suspend fun registerUser(registerPostModel: RegisterPostModel) : Flow<Resource<RegisterModel>>
    = flow {
        emit(Resource.loading(null))

        val response=apiSource.registerUser(registerPostModel)

        when(response.status){
            Status.ERROR->{
                emit(Resource.error(response.message?:"Error",null))
            }
            Status.SUCCESS->{
                emit(Resource.success(response.data?.toRegisterModel()))
            }
            else->{}
        }
    }

    override suspend fun getCurrentUser(): Flow<Resource<UserModel>> = flow {
        emit(Resource.loading(null))
        val response=apiSource.getCurrentUser()
        when(response.status){
            Status.ERROR->{
                emit(Resource.error(response.message?:"Error",null))
            }
            Status.SUCCESS->{
                emit(Resource.success(response.data?.toUserModel()))
            }
            else->{}
        }
    }

    override suspend fun getProducts(): Flow<Resource<List<ProductModel>>> = flow {
        emit(Resource.loading(null))
        val response=apiSource.getProducts()
        when(response.status){
            Status.SUCCESS->{
               emit(Resource.success(response.data?.toProductModel()))
            }
            Status.ERROR->{
                emit(Resource.error(response.message?:"Error",null))
            }
            else->{}
        }
    }

    override suspend fun getCategories(): Flow<Resource<List<CategoryModel>>> = flow{
        emit(Resource.loading(null))
        val response=apiSource.getCategories()
        when(response.status){
             Status.ERROR->{
                 emit(Resource.error(response.message?:"Error",null))
             }
            Status.SUCCESS->{
                emit(Resource.success(response.data?.toCategoryModel()))
            }
            else->{}
        }
    }


}