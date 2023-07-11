package com.bashirli.lazastore.domain.use_case

import com.bashirli.lazastore.domain.model.remote.RegisterPostModel
import com.bashirli.lazastore.domain.repo.ApiRepository
import javax.inject.Inject

class RegisterUserUseCase @Inject constructor(
    private val repo:ApiRepository
) {

    suspend operator fun invoke(registerPostModel: RegisterPostModel)=repo.registerUser(registerPostModel)

}