package com.bashirli.lazastore.data.repo

import com.bashirli.lazastore.common.util.Resource
import com.bashirli.lazastore.common.util.Status
import com.bashirli.lazastore.data.mapper.toAuthModel
import com.bashirli.lazastore.data.mapper.toCartMainModel
import com.bashirli.lazastore.data.mapper.toCartModel
import com.bashirli.lazastore.data.mapper.toCategoryModel
import com.bashirli.lazastore.data.mapper.toMainProductModel
import com.bashirli.lazastore.data.mapper.toProfileModel
import com.bashirli.lazastore.data.mapper.toRegisterModel
import com.bashirli.lazastore.data.mapper.toSingleProductModel
import com.bashirli.lazastore.data.mapper.toUserModel
import com.bashirli.lazastore.data.source.ApiSource
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
import com.bashirli.lazastore.domain.repo.ApiRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ApiRepositoryImpl @Inject constructor(
        private val apiSource: ApiSource
) : ApiRepository {

    override suspend fun loginUser(username: String, password: String): Flow<Resource<AuthModel>>
    = flow{
        emit(Resource.loading(null))
        val response=apiSource.loginUser(username, password)
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

    override suspend fun getCurrentProfile(): Flow<Resource<ProfileModel>> = flow {
        emit(Resource.loading(null))
        val response=apiSource.getCurrentUser()
        when(response.status){
            Status.ERROR->{
                emit(Resource.error(response.message?:"Error",null))
            }
            Status.SUCCESS->{
                emit(Resource.success(response.data?.toProfileModel()))
            }
            else->{}
        }
    }

    override suspend fun getProducts(): Flow<Resource<MainProductModel>> = flow {
        emit(Resource.loading(null))
        val response=apiSource.getProducts()
        when(response.status){
            Status.SUCCESS->{
               emit(Resource.success(response.data?.toMainProductModel()))
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

    override suspend fun getCategoryProducts(category:String): Flow<Resource<MainProductModel>> = flow {
        emit(Resource.loading(null))
        val response=apiSource.getCategoryProducts(category)
        when(response.status){
            Status.SUCCESS->{
                emit(Resource.success(response.data?.toMainProductModel()))
            }
            Status.ERROR->{
                emit(Resource.error(response.message?:"Error",null))
            }
            else->{}
        }
    }



    override suspend fun getSingleProduct(id: Int): Flow<Resource<SingleProductModel>> = flow{
        emit(Resource.loading(null))
        val response=apiSource.getSingleProduct(id)
        when(response.status){
            Status.SUCCESS->{
                emit(Resource.success(response.data?.toSingleProductModel()))
            }
            Status.ERROR->{
                emit(Resource.error(response.message?:"Error",null))
            }
            else->{}
        }
    }

    override suspend fun getCurrentUserCart(): Flow<Resource<CartMainModel>> = flow {
       emit(Resource.loading(null))
        val response=apiSource.getCurrentUserCart()
        when(response.status){
            Status.ERROR->{
                emit(Resource.error(response.message?:"Error",null))
            }
            Status.SUCCESS->{
                emit(Resource.success(response.data?.toCartMainModel()))
            }
            else->{}
        }
    }

    override suspend fun updateUserCart(
        updateCartBody: UpdateCartBody,
        cartId: Int,
    ): Flow<Resource<CartModel>> = flow{
        emit(Resource.loading(null))
        val response=apiSource.updateUserCart(updateCartBody, cartId)
        when(response.status){
            Status.ERROR->{
                emit(Resource.error(response.message?:"Error",null))
            }
            Status.SUCCESS->{
                emit(Resource.success(response.data?.toCartModel()))
            }
            else->{}
        }
    }

    override suspend fun getSearchProducts(searchText: String): Flow<Resource<MainProductModel>> = flow {
        emit(Resource.loading(null))
        val response=apiSource.getSearchProducts(searchText)
        when(response.status){
            Status.ERROR->{
                emit(Resource.error(response.message?:"Error",null))
            }
            Status.SUCCESS->{
                emit(Resource.success(response.data?.toMainProductModel()))
            }
            else->{}
        }
    }

}