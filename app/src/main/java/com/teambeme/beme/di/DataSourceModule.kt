package com.teambeme.beme.di

import com.teambeme.beme.data.remote.api.*
import com.teambeme.beme.data.remote.datasource.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {
    @Provides
    @Singleton
    fun provideAnswerDataSource(answerService: AnswerService): AnswerDataSource =
        AnswerDataSourceImpl(answerService)

    @Provides
    @Singleton
    fun provideDetailDataSource(detailService: DetailService): DetailDataSource =
        DetailDataSourceImpl(detailService)

    @Provides
    @Singleton
    fun provideExploreDataSource(exploreService: ExploreService): ExploreDataSource =
        ExploreDataSourceImpl(exploreService)

    @Provides
    @Singleton
    fun provideTokenRegisterDataSource(fbTokenRegisterService: FbTokenRegisterService): FbTokenRegisterDataSource =
        FbTokenRegisterDataSourceImpl(fbTokenRegisterService)

    @Provides
    @Singleton
    fun provideFollowingDataSource(followingService: FollowingService): FollowingDataSource =
        FollowingDataSourceImpl(followingService)

    @Provides
    @Singleton
    fun provideHomeDataSource(homeService: HomeService): HomeDataSource =
        HomeDataSourceImpl(homeService)

    @Provides
    @Singleton
    fun provideIdSearchDataSource(idSearchService: IdSearchService): IdSearchDataSource =
        IdSearchDataSourceImpl(idSearchService)

    @Provides
    @Singleton
    fun provideLoginDataSource(loginService: LoginService): LoginDataSource =
        LoginDataSourceImpl(loginService)

    @Provides
    @Singleton
    fun provideMyPageDataSource(myPageService: MyPageService): MyPageDataSource =
        MyPageDataSourceImpl(myPageService)

    @Provides
    @Singleton
    fun provideNoticeDataSource(noticeService: NoticeService): NoticeDataSource =
        NoticeDataSourceImpl(noticeService)

    @Provides
    @Singleton
    fun provideOtherPageDataSource(otherService: OtherService): OtherPageDataSource =
        OtherPageDataSourceImpl(otherService)

    @Provides
    @Singleton
    fun provideSignUpDataSource(signUpService: SignUpService): SignUpDataSource =
        SignUpDataSourceImpl(signUpService)
}
