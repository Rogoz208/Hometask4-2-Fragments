package com.rogoz208.hometask4_2_fragments.di

import com.rogoz208.hometask4_2_fragments.data.repos.MemoryCacheUsersMockRepoImpl
import com.rogoz208.hometask4_2_fragments.domain.repos.UsersRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideUsersRepo(): UsersRepo = MemoryCacheUsersMockRepoImpl()
}