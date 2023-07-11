package com.bashirli.lazastore.di

import android.content.Context
import androidx.room.Room
import com.bashirli.lazastore.data.repo.ApiRepositoryImpl
import com.bashirli.lazastore.data.repo.RoomRepoImpl
import com.bashirli.lazastore.data.service.local.RoomDAO
import com.bashirli.lazastore.data.service.local.RoomDB
import com.bashirli.lazastore.data.source.RoomSource
import com.bashirli.lazastore.data.source.RoomSourceImpl
import com.bashirli.lazastore.domain.repo.RoomRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalModule {

    @Singleton
    @Provides
    fun injectRoomDB(@ApplicationContext context:Context) = Room.databaseBuilder(context,RoomDB::class.java,"FavoritesDB").build()

    @Singleton
    @Provides
    fun injectRoomDAO(roomDB: RoomDB)=roomDB.getDao()

    @Singleton
    @Provides
    fun injectRepo(roomSource: RoomSource)=RoomRepoImpl(roomSource) as RoomRepository

    @Singleton
    @Provides
    fun injectSource(roomDAO: RoomDAO) = RoomSourceImpl(roomDAO) as RoomSource

}