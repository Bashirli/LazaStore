package com.bashirli.lazastore.domain.use_case

import com.bashirli.lazastore.domain.repo.ApiRepository
import javax.inject.Inject

class GetCategoryProductsUseCase @Inject constructor(
    private val repo:ApiRepository
) {

    suspend operator fun invoke(id:Int)=repo.getCategoryProducts(id)

}