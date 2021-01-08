# StatusBarUtil

## 상태바 색상 바꾸기

안드로이드의 StatusBar의 색을 바꾸고 아이콘 색상을 바꾸기 위해서 다음과 같은 코드를 추가한다

```kotlin
object StatusBarUtil {
    @SuppressLint("InlinedApi")
    @Suppress("DEPRECATION")
    fun setStatusBar(activity: Activity, color: Int) {
      	// 아이콘 색상을 어둡게 해줍니다
        activity.window?.decorView?.systemUiVisibility =
            View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
      	// 상태바 색상을 color의 색으로 맞춰줍니다
        activity.window?.statusBarColor = color
    }
}
```



이제 Main Activity에서 다음과 같이 적용한다

```kotlin
bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.menu_main_home -> {
                    binding.vpMain.currentItem = 0
                    StatusBarUtil.setStatusBar(this, Color.BLACK)
                }
                R.id.menu_main_explore -> {
                    binding.vpMain.currentItem = 1
                    StatusBarUtil.setStatusBar(
                        this,
                        resources.getColor(R.color.explore_background_gray, null)
                    )
                }
                R.id.menu_main_following -> {
                    binding.vpMain.currentItem = 2
                    StatusBarUtil.setStatusBar(this, resources.getColor(R.color.white, null))
                }
                R.id.menu_main_mypage -> {
                    binding.vpMain.currentItem = 3
                    StatusBarUtil.setStatusBar(this, resources.getColor(R.color.white, null))
                }
            }
            true
}
```

위와 같이 작성하면 바텀 네비게이션에서 페이지를 넘길 때마다 Status Bar의 색상을 바꿀 수 있다.



## 구현 화면

<img src="https://user-images.githubusercontent.com/54518925/103991372-e82bca80-51d5-11eb-8765-ba033075dd53.gif" width="40%"/>