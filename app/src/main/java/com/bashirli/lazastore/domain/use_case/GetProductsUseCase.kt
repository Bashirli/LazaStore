package com.bashirli.lazastore.domain.use_case

import com.bashirli.lazastore.domain.repo.ApiRepository
import javax.inject.Inject

class GetProductsUseCase @Inject constructor(
    private val repo:ApiRepository
) {

    suspend operator fun invoke()=repo.getProducts()

}