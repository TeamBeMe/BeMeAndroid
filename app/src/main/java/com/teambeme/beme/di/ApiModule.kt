package com.teambeme.beme.di

import com.teambeme.beme.data.remote.api.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {
    @Provides
    @Singleton
    fun provideAnswerService(retrofit: Retrofit): AnswerService =
        retrofit.create(AnswerService::class.java)

    @Provides
    @Singleton
    fun provideDetailService(retrofit: Retrofit): DetailService =
        retrofit.create(DetailService::class.java)

    @Provides
    @Singleton
    fun provideExploreService(retrofit: Retrofit): ExploreService =
        retrofit.create(ExploreService::class.java)

    @Provides
    @Singleton
    fun provideFbTokenService(retrofit: Retrofit): FbTokenRegisterService =
        retrofit.create(FbTokenRegisterService::class.java)

    @Provides
    @Singleton
    fun provideFollowingService(retrofit: Retrofit): FollowingService =
        retrofit.create(FollowingService::class.java)

    @Provides
    @Singleton
    fun provideHomeService(retrofit: Retrofit): HomeService =
        retrofit.create(HomeService::class.java)

    @Provides
    @Singleton
    fun provideIdSearchService(retrofit: Retrofit): IdSearchService =
        retrofit.create(IdSearchService::class.java)

    @Provides
    @Singleton
    fun provideLoginService(retrofit: Retrofit): LoginService =
        retrofit.create(LoginService::class.java)

    @Provides
    @Singleton
    fun provideMyPageService(retrofit: Retrofit): MyPageService =
        retrofit.create(MyPageService::class.java)

    @Provides
    @Singleton
    fun provideNoticeService(retrofit: Retrofit): NoticeService =
        retrofit.create(NoticeService::class.java)

    @Provides
    @Singleton
    fun provideOtherService(retrofit: Retrofit): OtherService =
        retrofit.create(OtherService::class.java)

    @Provides
    @Singleton
    fun provideSignUpService(retrofit: Retrofit): SignUpService =
        retrofit.create(SignUpService::class.java)
}
