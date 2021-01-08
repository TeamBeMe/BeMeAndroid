# 중첩 ViewPager2 이슈를 NestedScorllableHost로 해결하기

## 사용 이유

ViewPager2 내부에 새로운 ViewPager2를 사용할 경우 내부 ViewPager2가 스크롤이 안되는 이슈가 발생한다. 이를 구글에서 검색한 결과 이 이슈를 해결하기 위해서는 Google ViewPager2 repo 내 testapp folder에 있는 NestedScrollableHost View를 제공하고 있다.

[NestedScrollableHost Link](https://github.com/android/views-widgets-samples/blob/master/ViewPager2/app/src/main/java/androidx/viewpager2/integration/testapp/NestedScrollableHost.kt)



## 사용방법

```kotlin
<com.teambeme.beme.util.NestedScrollableHost
            android:layout_width="match_parent"
            android:layout_height="0dp"
            android:layout_marginTop="24dp"
            android:layout_marginBottom="80dp"
            app:layout_constraintBottom_toBottomOf="parent"
            app:layout_constraintTop_toBottomOf="@+id/txt_home_title">

        <androidx.viewpager2.widget.ViewPager2
            android:id="@+id/vp_home_question_slider"
            android:layout_width="match_parent"
            android:layout_height="match_parent" />
</com.teambeme.beme.util.NestedScrollableHost>
```



## 적용화면

<img src="https://user-images.githubusercontent.com/54518925/103989529-312e4f80-51d3-11eb-9d8f-25e171c3f667.gif" width="40%"/>