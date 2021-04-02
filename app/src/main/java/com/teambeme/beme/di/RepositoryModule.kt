package com.teambeme.beme.di

import com.teambeme.beme.data.local.dao.AnswerDao
import com.teambeme.beme.data.remote.datasource.*
import com.teambeme.beme.data.repository.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun provideAnswerRepository(
        answerDao: AnswerDao,
        answerDataSource: AnswerDataSource
    ): AnswerRepository =
        AnswerRepositoryImpl(answerDao, answerDataSource)

    @Provides
    @Singleton
    fun provideDetailRepository(detailDataSource: DetailDataSource): DetailRepository =
        DetailRepositoryImpl(detailDataSource)

    @Provides
    @Singleton
    fun provideExploreRepository(exploreDataSource: ExploreDataSource): ExploreRepository =
        ExploreRepositoryImpl(exploreDataSource)

    @Provides
    @Singleton
    fun provideFollowingRepository(followingDataSource: FollowingDataSource): FollowingRepository =
        FollowingRepositoryImpl(followingDataSource)

    @Provides
    @Singleton
    fun provideHomeRepository(homeDataSource: HomeDataSource): HomeRepository =
        HomeRepositoryImpl(homeDataSource)

    @Provides
    @Singleton
    fun provideIdSearchRepository(idSearchDataSource: IdSearchDataSource): IdSearchRepository =
        IdSearchRepositoryImpl(idSearchDataSource)

    @Provides
    @Singleton
    fun provideLoginRepository(loginDataSource: LoginDataSource): LoginRepository =
        LoginRepositoryImpl(loginDataSource)

    @Provides
    @Singleton
    fun provideMainRepository(fbTokenRegisterDataSource: FbTokenRegisterDataSource): MainRepository =
        MainRepositoryImpl(fbTokenRegisterDataSource)

    @Provides
    @Singleton
    fun provideMyPageRepository(myPageDataSource: MyPageDataSource): MyPageRepository =
        MyPageRepositoryImpl(myPageDataSource)

    @Provides
    @Singleton
    fun provideNoticeRepository(noticeDataSource: NoticeDataSource): NoticeRepository =
        NoticeRepositoryImpl(noticeDataSource)

    @Provides
    @Singleton
    fun provideOtherPageRepository(otherPageDataSource: OtherPageDataSource): OtherPageRepository =
        OtherPageRepositoryImpl(otherPageDataSource)

    @Provides
    @Singleton
    fun provideSignUpRepository(signUpDataSource: SignUpDataSource): SignUpRepository =
        SignUpRepositoryImpl(signUpDataSource)
}