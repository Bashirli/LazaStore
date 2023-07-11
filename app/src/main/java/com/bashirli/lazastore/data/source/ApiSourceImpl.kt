package com.bashirli.lazastore.data.source

import com.bashirli.lazastore.common.util.Resource
import com.bashirli.lazastore.common.util.findExceptionMessage
import com.bashirli.lazastore.common.util.findExceptionMessageList
import com.bashirli.lazastore.data.dto.remote.AuthDTO
import com.bashirli.lazastore.data.dto.remote.CategoryDTO
import com.bashirli.lazastore.data.dto.remote.product.ProductDTO
import com.bashirli.lazastore.data.dto.remote.SingleProductDTO
import com.bashirli.lazastore.data.dto.remote.cart.CartDTO
import com.bashirli.lazastore.data.dto.remote.cart.CartUpdateDTO
import com.bashirli.lazastore.data.dto.remote.register.RegisterDTO
import com.bashirli.lazastore.data.dto.remote.search.SearchDTO
import com.bashirli.lazastore.data.dto.remote.user.UserDTO
import com.bashirli.lazastore.data.service.remote.Service
import com.bashirli.lazastore.domain.model.remote.RegisterPostModel
import com.bashirli.lazastore.domain.model.remote.body.UpdateCartBody
import javax.inject.Inject

class ApiSourceImpl @Inject constructor(
    private val service: Service
) : ApiSource {

    override suspend fun loginUser(username: String, password: String): Resource<AuthDTO> {
        return try{
            val response=service.loginUser(username, password)

            if(response.isSuccessful){
                response.body()?.let {

                    Resource.success(it)
                }?:Resource.error("Error",null)
            }else{
               val errorMessage = findExceptionMessage(response.errorBody())
                Resource.error(errorMessage,null)
            }
        }catch (e:Exception){
            Resource.error(e.localizedMessage,null)
        }
    }

    override suspend fun registerUser(registerPostModel: RegisterPostModel): Resource<RegisterDTO> {
        return try{
            val response=service.registerUser(registerPostModel)
            if(response.isSuccessful){
                response.body()?.let {
                    Resource.success(it)
                }?:Resource.error("Error",null)
            }else{
                val errorMessage = findExceptionMessageList(response.errorBody())
                Resource.error(errorMessage,null)
            }
        }catch (e:Exception){
            Resource.error(e.localizedMessage,null)
        }
    }

    override suspend fun getProducts(): Resource<ProductDTO> {
        return try{
            val response=service.getProducts()
            if(response.isSuccessful){
                response.body()?.let {
                    Resource.success(it)
                }?:Resource.error("Error",null)
            }else{
                val errorMessage=findExceptionMessage(response.errorBody())
                Resource.error(errorMessage,null)
            }
        }catch (e:Exception){
            Resource.error(e.localizedMessage,null)
        }
    }

    override suspend fun getCategories(): Resource<CategoryDTO> {
        return try{
            val response=service.getCategories()
            if(response.isSuccessful){
                response.body()?.let {
                    Resource.success(it)
                }?:Resource.error("Error",null)
            }else{
                val errorMessage=findExceptionMessage(response.errorBody())
                Resource.error(errorMessage,null)
            }
        }catch (e:Exception){
            Resource.error(e.localizedMessage,null)
        }
    }

    override suspend fun getCurrentUser(): Resource<UserDTO> {
        return try{
            val response=service.getCurrentUser()
            if(response.isSuccessful){
                response.body()?.let {
                    Resource.success(it)
                }?:Resource.error("Error",null)
            }else{
                val errorMessage=findExceptionMessage(response.errorBody())
                Resource.error(errorMessage,null)
            }
        }catch (e:Exception){
            Resource.error(e.localizedMessage,null)
        }
    }

    override suspend fun getCurrentProfile(): Resource<UserDTO> {
        return try{
            val response=service.getCurrentUser()
            if(response.isSuccessful){
                response.body()?.let {
                    Resource.success(it)
                }?:Resource.error("Error",null)
            }else{
                val errorMessage=findExceptionMessage(response.errorBody())
                Resource.error(errorMessage,null)
            }
        }catch (e:Exception){
            Resource.error(e.localizedMessage,null)
        }
    }

    override suspend fun getCategoryProducts(category:String): Resource<ProductDTO> {
        return try{
            val response=service.getCategoryProducts(category)
            if(response.isSuccessful){
                response.body()?.let {
                    Resource.success(it)
                }?:Resource.error("Error",null)
            }else{
                val errorMessage=findExceptionMessage(response.errorBody())
                Resource.error(errorMessage,null)
            }
        }catch (e:Exception){
            Resource.error(e.localizedMessage,null)
        }
    }



    override suspend fun getSingleProduct(id: Int): Resource<SingleProductDTO> {
        return try{
            val response=service.getSingleProduct(id)
            if(response.isSuccessful){
                response.body()?.let {
                    Resource.success(it)
                }?:Resource.error("Error",null)
            }else{
                val errorMessage=findExceptionMessage(response.errorBody())
                Resource.error(errorMessage,null)
            }
        }catch (e:Exception){
            Resource.error(e.localizedMessage,null)
        }
    }

    override suspend fun getCurrentUserCart(): Resource<CartDTO> {
        return try{
            val response=service.getCurrentUserCart()
            if(response.isSuccessful){
                response.body()?.let {
                    Resource.success(it)
                }?:Resource.error("Error",null)
            }else{
                val errorMessage=findExceptionMessage(response.errorBody())
                Resource.error(errorMessage,null)
            }
        }catch (e:Exception){
            Resource.error(e.localizedMessage,null)
        }
    }

    override suspend fun updateUserCart(updateCartBody: UpdateCartBody, cartId:Int): Resource<CartUpdateDTO> {
        return try{
            val response=service.updateUserCart(updateCartBody,cartId)
            if(response.isSuccessful){
                response.body()?.let {
                    Resource.success(it)
                }?:Resource.error("Error",null)
            }else{
                val errorMessage=findExceptionMessage(response.errorBody())
                Resource.error(errorMessage,null)
            }
        }catch (e:Exception){
            Resource.error(e.localizedMessage,null)
        }
    }

    override suspend fun getSearchProducts(searchText: String): Resource<SearchDTO> {
        return try{
            val response=service.getSearchProducts(searchText)
            if(response.isSuccessful){
                response.body()?.let {
                    Resource.success(it)
                }?:Resource.error("Error",null)
            }else{
                val errorMessage=findExceptionMessage(response.errorBody())
                Resource.error(errorMessage,null)
            }
        }catch (e:Exception){
            Resource.error(e.localizedMessage,null)
        }
    }

}