package com.teambeme.beme.di

import com.teambeme.beme.domain.usecase.LikeUseCase
import com.teambeme.beme.domain.usecase.LikeUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class UseCaseModule {
    @Binds
    abstract fun bindLikeUseCase(useCaseImpl: LikeUseCaseImpl): LikeUseCase
}
