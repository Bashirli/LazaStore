package com.bashirli.lazastore.domain.use_case

import com.bashirli.lazastore.domain.model.body.UpdateCartBody
import com.bashirli.lazastore.domain.repo.ApiRepository
import javax.inject.Inject

class UpdateUserCartUseCase @Inject constructor(private val repo:ApiRepository) {

    suspend operator fun invoke(updateCartBody: UpdateCartBody,cartId:Int)=repo.updateUserCart(updateCartBody, cartId)

}