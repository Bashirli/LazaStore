package com.bashirli.lazastore.domain.use_case

import com.bashirli.lazastore.domain.repo.ApiRepository
import javax.inject.Inject


class LoginUserUseCase @Inject constructor(
    private val repo:ApiRepository
){
    suspend operator fun invoke(email:String,password:String)=repo.loginUser(email, password)


}