package com.bashirli.lazastore.di

import android.content.Context
import android.content.SharedPreferences
import com.bashirli.lazastore.common.TokenManager
import com.bashirli.lazastore.common.util.AppSharedPreferances
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object SpModule {

    @Singleton
    @Provides
    fun injectSP(
        @ApplicationContext context:Context
    ) = context
        .getSharedPreferences(
            AppSharedPreferances.AUTH,
            Context.MODE_PRIVATE
        )

    @Singleton
    @Provides
    fun injectAppSP(sp:SharedPreferences)= AppSharedPreferances(sp)

    @Singleton
    @Provides
    fun injectTokenManager(sp: AppSharedPreferances)= TokenManager(sp)

}