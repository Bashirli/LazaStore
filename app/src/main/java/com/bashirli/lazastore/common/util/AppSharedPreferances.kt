package com.bashirli.lazastore.common.util

import android.content.SharedPreferences
import javax.inject.Inject

class AppSharedPreferances @Inject constructor(
    private val sp: SharedPreferences
) {

    fun setToken(token:String){
        sp.edit().putString(TOKEN,token).apply()
    }

    fun removeToken(){
        sp.edit().remove(TOKEN).apply()
    }

    fun getToken():String?{
        return sp.getString(TOKEN,null)
    }

    companion object{
        const val AUTH="AUTH"
        const val TOKEN="TOKEN"
    }

}