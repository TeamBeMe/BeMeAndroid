package com.teambeme.beme.data.remote.singleton

import com.teambeme.beme.data.remote.api.*
import com.teambeme.beme.util.AuthInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitObjects {
    private const val BASE_URL = "http://52.79.42.85:3000/"

    private fun httpLoggingInterceptor(): HttpLoggingInterceptor {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return loggingInterceptor
    }

    private fun getOkHttpClient(): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(httpLoggingInterceptor())
        .addInterceptor(AuthInterceptor())
        .build()

    private val baseRetrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(getOkHttpClient())
        .build()

    private var loginInstance: LoginService? = null
    fun getLoginService(): LoginService = loginInstance ?: synchronized(this) {
        loginInstance ?: baseRetrofit.create(LoginService::class.java).apply {
            loginInstance = this
        }
    }

    private var exploreInstance: ExploreService? = null
    fun getExploreService(): ExploreService = exploreInstance ?: synchronized(this) {
        exploreInstance ?: baseRetrofit.create(ExploreService::class.java).apply {
            exploreInstance = this
        }
    }

    private var otherPageInstance: OtherService? = null
    fun getOtherPageService(): OtherService = otherPageInstance ?: synchronized(this) {
        otherPageInstance ?: baseRetrofit.create(OtherService::class.java).apply {
            otherPageInstance = this
        }
    }

    private var signUpInstance: SignUpService? = null
    fun getSignUpService(): SignUpService = signUpInstance ?: synchronized(this) {
        signUpInstance ?: baseRetrofit.create(SignUpService::class.java).apply {
            signUpInstance = this
        }
    }

    private var MyPageInstance: MyPageService? = null
    fun getMyPageService(): MyPageService = MyPageInstance ?: synchronized(this) {
        MyPageInstance ?: baseRetrofit.create(MyPageService::class.java).apply {
            MyPageInstance = this
        }
    }

    private var homeInstance: HomeService? = null
    fun getHomeService(): HomeService = homeInstance ?: synchronized(this) {
        homeInstance ?: baseRetrofit.create(HomeService::class.java).apply {
            homeInstance = this
        }
    }

    private var followingInstance: FollowingService? = null
    fun getFollowingService(): FollowingService = followingInstance ?: synchronized(this) {
        followingInstance ?: baseRetrofit.create(FollowingService::class.java).apply {
            followingInstance = this
        }
    }

    private var idSearchInstance: IdSearchService? = null
    fun getIdSearchService(): IdSearchService = idSearchInstance ?: synchronized(this) {
        idSearchInstance ?: baseRetrofit.create(IdSearchService::class.java).apply {
            idSearchInstance = this
        }
    }

    private var noticeInstance: NoticeService? = null
    fun getNoticeService(): NoticeService = noticeInstance ?: synchronized(this) {
        noticeInstance ?: baseRetrofit.create(NoticeService::class.java).apply {
            noticeInstance = this
        }
    }

    private var fbTokenRegisterInstance: FbTokenRegisterService? = null
    fun getFbTokenRegisterService(): FbTokenRegisterService = fbTokenRegisterInstance ?: synchronized(this) {
        fbTokenRegisterInstance ?: baseRetrofit.create(FbTokenRegisterService::class.java).apply {
            fbTokenRegisterInstance = this
        }
    }

    private var answerInstance: AnswerService? = null
    fun getAnswerService(): AnswerService = answerInstance ?: synchronized(this) {
        answerInstance ?: baseRetrofit.create(AnswerService::class.java).apply {
            answerInstance = this
        }
    }

    private var detailInstance: DetailService? = null
    fun getDetailService(): DetailService = detailInstance ?: synchronized(this) {
        detailInstance ?: baseRetrofit.create(DetailService::class.java).apply {
            detailInstance = this
        }
    }
}