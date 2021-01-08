# Base ViewController 사용설명서

## 구현 의도

Data Binding을 대부분 View Controller에서 사용하는데 모든 View Controller에 Data Binding 코드를 일일이 적는 것 자체가 보일러 플레이트라 생각하였다. 또한 뷰의 생명주기에 관련된 버그가 많이 발생하는데 이를 좀 더 편하게 잡기 위해 LifeCycle의 상태를 알 수 있는 함수를 protected 함수로 만들어 생명주기 디버깅을 용이하게 하고자 했다.



## 구현 코드

### Binding Acitivity

```kotlin
abstract class BindingActivity<T : ViewDataBinding>(@LayoutRes private val layoutResId: Int) :
    AppCompatActivity() {
    protected lateinit var binding: T

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, layoutResId)
    }

    protected inner class LifeCycleEventLogger(private val className: String) : LifecycleObserver {
        fun registerLogger(lifecycle: Lifecycle) {
            lifecycle.addObserver(this)
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_ANY)
        fun log() {
            Log.d("${className}LifeCycleEvent", "${lifecycle.currentState}")
        }
    }
}
```



### Binding Fragment

```kotlin
abstract class BindingFragment<T : ViewDataBinding>(@LayoutRes private val layoutResId: Int) :
    Fragment() {
    protected lateinit var binding: T

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, layoutResId, container, false)
        return binding.root
    }

    protected inner class LifeCycleEventLogger(private val className: String) : LifecycleObserver {
        fun registerLogger(lifecycle: Lifecycle) {
            lifecycle.addObserver(this)
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_ANY)
        fun log() {
            Log.d("${className}LifeCycleEvent", "${lifecycle.currentState}")
        }
    }
}
```



## Base Activity

- Activity 단에서 사용가능

  ```kotlin
  class MainActivity : BindingActivity<ActivityMainBinding>(R.layout.activity_main) {
      private val mainViewModel: MainViewModel by viewModels()
      override fun onCreate(savedInstanceState: Bundle?) {
          super.onCreate(savedInstanceState)
          LifeCycleEventLogger(javaClass.name).registerLogger(lifecycle)
      }
    // 이하 생략
  }
  ```

  - BindingActivity<데이터바인딩 클래스>(액티비티 레이아웃) 으로 넣어주기

  - onCreate함수에서 반드시 super.onCreate 함수 호출

  - 데이터 바인딩 설정은 각 Activity에서 안해도 됨

  - LifeCycle이 변하는 것을 로그로 찍고 싶으면

    ```kotlin
    LifeCycleEventLogger(javaClass.name).registerLogger(lifecycle)
    ```

    만 코드로 넣어주기



## BaseFragment

- Fragment 단에서 사용가능

  ```kotlin
  class HomeFragment : BindingFragment<FragmentHomeBinding>(R.layout.fragment_home) {
      override fun onCreateView(
          inflater: LayoutInflater,
          container: ViewGroup?,
          savedInstanceState: Bundle?
      ): View {
          super.onCreateView(inflater, container, savedInstanceState)
          LifeCycleEventLogger(javaClass.name).registerLogger(viewLifecycleOwner.lifecycle)
          return binding.root
      }
  }
  ```

  - BindingFramgnet<데이터바인딩 클래스>(프래그먼트 레이아웃)으로 넣어주기

  - onCreateView 함수에서 반드시 super.onCreateView 호출하기

  - LifeCycle이 변하는 것을 로그로 찍고 싶으면

    ```kotlin
    LifeCycleEventLogger(javaClass.name).registerLogger(viewLifecycleOwner.lifecycle)
    ```

    만 코드로 넣어주기



## 생명주기 로그 사진

<img src= "https://user-images.githubusercontent.com/54518925/103987470-dc3d0a00-51cf-11eb-9d33-a39889f87b06.png" width="40%" />

