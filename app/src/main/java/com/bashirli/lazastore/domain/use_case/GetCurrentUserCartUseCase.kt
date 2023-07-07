package com.bashirli.lazastore.domain.use_case

import com.bashirli.lazastore.domain.repo.ApiRepository
import javax.inject.Inject

class GetCurrentUserCartUseCase @Inject constructor(
    private val repository: ApiRepository
) {

    suspend operator fun invoke()=repository.getCurrentUserCart()

}