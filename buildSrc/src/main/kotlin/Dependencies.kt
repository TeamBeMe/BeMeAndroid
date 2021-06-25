object KotlinDependencies {
    const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlinVersion}"
}

object AndroidXDependencies {
    const val coreKtx = "androidx.core:core-ktx:${Versions.coreKtxVersion}"
    const val appCompat = "androidx.appcompat:appcompat:${Versions.appCompatVersion}"
    const val constraintLayout =
        "androidx.constraintlayout:constraintlayout:${Versions.constraintLayoutVersion}"
    const val hilt = "com.google.dagger:hilt-android:${Versions.hiltVersion}"
    const val navigationFragment =
        "androidx.navigation:navigation-fragment-ktx:${Versions.navVersion}"
    const val navigationUI = "androidx.navigation:navigation-ui-ktx:${Versions.navVersion}"
    const val paging3 = "androidx.paging:paging-runtime:${Versions.pagingVersion}"
    const val fragment = "androidx.fragment:fragment-ktx:${Versions.fragmentKtxVersion}"
    const val legacy = "androidx.legacy:legacy-support-v4:${Versions.legacySupportVersion}"
    const val room = "androidx.room:room-runtime:${Versions.roomVersion}"
    const val roomKtx = "androidx.room:room-ktx:${Versions.roomVersion}"
    const val security = "androidx.security:security-crypto:${Versions.securityVersion}"
    const val coroutines =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutinesAndroidVersion}"
}

object TestDependencies {
    const val jUnit = "junit:junit:${Versions.junitVersion}"
    const val androidTest = "androidx.test.ext:junit:${Versions.androidTestVersion}"
    const val espresso = "androidx.test.espresso:espresso-core:${Versions.espressoVersion}"
    const val room = "androidx.room:room-testing:${Versions.roomVersion}"
}

object MaterialDesignDependencies {
    const val materialDesign =
        "com.google.android.material:material:${Versions.materialDesignVersion}"
}

object KaptDependencies {
    const val hiltCompiler = "com.google.dagger:hilt-compiler:${Versions.hiltVersion}"
    const val roomCompiler = "androidx.room:room-compiler:${Versions.roomVersion}"
}

object ThirdPartyDependencies {
    const val playCore = "com.google.android.play:core:${Versions.playCoreVersion}"
    const val gson = "com.google.code.gson:gson:${Versions.gsonVersion}"
    const val glide = "com.github.bumptech.glide:glide:${Versions.glideVersion}"
    const val lottie = "com.airbnb.android:lottie:${Versions.lottieVersion}"
    const val tedPermission = "gun0912.ted:tedpermission:${Versions.tedPermissionVersion}"
    const val imageCropper =
        "com.theartofdev.edmodo:android-image-cropper:${Versions.imageCropperVersion}"
    const val stickyScrollView =
        "com.github.didikk:sticky-nestedscrollview:${Versions.stickyScrollViewVersion}"
    const val viewPagerIndicator =
        "com.tbuonomo.andrui:viewpagerdotsindicator:${Versions.viewPagerIndicatorVersion}"
    const val pullRefreshLayout =
        "com.baoyz.pullrefreshlayout:library:${Versions.pullRefreshLayoutVersion}"
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofitVersion}"
    const val retrofitGsonConverter =
        "com.squareup.retrofit2:converter-gson:${Versions.retrofitVersion}"
    const val okHttp = "com.squareup.okhttp3:okhttp:${Versions.okHttpVersion}"
    const val okHttpLogginInterceptor =
        "com.squareup.okhttp3:logging-interceptor:${Versions.okHttpLogginVersion}"
    const val circleImageView = "de.hdodenhof:circleimageview:${Versions.circleImageViewVersion}"

}

object FirebaseDependency {
    const val firebaseBom = "com.google.firebase:firebase-bom:${Versions.firebaseBomVersion}"
    const val analyticsKtx = "com.google.firebase:firebase-analytics-ktx"
    const val firebaeMessaging =
        "com.google.firebase:firebase-messaging:${Versions.firebaseMessagingVersion}"
}

object ClassPathPlugins {
    const val hilt = "com.google.dagger:hilt-android-gradle-plugin:${Versions.hiltVersion}"
    const val googleService = "com.google.gms:google-services:${Versions.googleServiceVersion}"
}