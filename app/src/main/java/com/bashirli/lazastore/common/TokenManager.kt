package com.bashirli.lazastore.common

import com.bashirli.lazastore.common.util.AppSharedPreferances
import javax.inject.Inject

class TokenManager @Inject constructor(
    private val sp: AppSharedPreferances
) {

    fun setToken(token:String){
        sp.setToken(token)
    }

    fun removeToken(){
        sp.removeToken()
    }

    fun getToken():String?{
        return sp.getToken()
    }

}