package com.bashirli.lazastore.di

import com.bashirli.lazastore.BASE_URL
import com.bashirli.lazastore.common.TokenManager
import com.bashirli.lazastore.data.repo.ApiRepositoryImpl
import com.bashirli.lazastore.domain.repo.ApiRepository
import com.bashirli.lazastore.data.service.remote.ApiKeyInterceptor
import com.bashirli.lazastore.data.service.remote.Service
import com.bashirli.lazastore.data.source.ApiSource
import com.bashirli.lazastore.data.source.ApiSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {


    @Singleton
    @Provides
    fun injectInterceptor(tokenManager: TokenManager)= ApiKeyInterceptor(tokenManager)

    @Singleton
    @Provides
    fun injectOkHttp3(interceptor: ApiKeyInterceptor)=OkHttpClient.Builder().addInterceptor(interceptor).build()

    @Singleton
    @Provides
    fun injectRetrofit(okHttpClient: OkHttpClient)=Retrofit
        .Builder()
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .build()

    @Singleton
    @Provides
    fun injectService(retrofit: Retrofit)=retrofit.create(Service::class.java)

    @Singleton
    @Provides
    fun injectApiSource(service: Service)=ApiSourceImpl(service) as ApiSource

    @Singleton
    @Provides
    fun injectRepo(apiSource: ApiSource)=ApiRepositoryImpl(apiSource) as ApiRepository




}