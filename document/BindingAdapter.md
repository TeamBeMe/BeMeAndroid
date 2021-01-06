# Binding Adapter
## xml에서 View의 attribute를 정의m 로직을 작성
- MVVM + LiveData와 함께 사용했다.

  - RecyclerView Listitem xml (list_explore_otherquestions.xml)<br>
    리사이클러뷰 어댑터가 가지고 있는 리스트의 각각의 원소들을 이 리스트아이템 xml과 바인딩시킨다.

    ```xml
    <layout xmlns:android="http://schemas.android.com/apk/res/android"
        xmlns:app="http://schemas.android.com/apk/res-auto">

        <data>

            <variable
                name="otherquestions"
                type="com.teambeme.beme.explore.model.OtherquestionsData" />
        </data>
    </layout>
    ```

  - ViewModel + LiveData 사용

    ```kotlin
    class ExploreViewModel : ViewModel() {
        private val _otherquestionsList = MutableLiveData<MutableList<OtherquestionsData>>()
        val otherquestionsList: LiveData<MutableList<OtherquestionsData>>
            get() = _otherquestionsList
    }
    ```

  - BindingAdapter

    ```kotlin
    @BindingAdapter("setOtherquestions")
    @JvmStatic
    fun setOtherquestions(
        recyclerView: RecyclerView,
        otherquestions: MutableList<OtherquestionsData>?
    ) {
        if (recyclerView.adapter != null) with(recyclerView.adapter as OtherquestionsRcvAdapter) {
            otherquestions?.let {
                submitList(otherquestions)
            }
        }
    }
    ```

  - BindingAdpater 적용한 부분 (fragment_explore.xml)

    ```xml
    <layout xmlns:android="http://schemas.android.com/apk/res/android"
        xmlns:app="http://schemas.android.com/apk/res-auto"
        xmlns:tools="http://schemas.android.com/tools">

        <data>

            <variable
                name="exploreViewModel"
                type="com.teambeme.beme.explore.viewmodel.ExploreViewModel" />
        </data>
    ...

      	<androidx.recyclerview.widget.RecyclerView
        	android:id="@+id/rcv_explore_otherquestions"
        	setOtherquestions="@{exploreViewModel.otherquestionsList}"
    ...

        	tools:listitem="@layout/list_explore_otherquestions"
    			/>
    </layout>
    ```