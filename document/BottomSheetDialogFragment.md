# 검색 필터 바텀시트 구성하고 뷰모델을 통해 값을 받아와 봅시다
## 바텀시트를 구성해주는 BottomSheetDialogFragment()

```
private fun filterClickListener(isFilterClicked: Boolean) {
        if (isFilterClicked) {
            val bottomSheetFragment = BottomWriteFragment()
            bottomSheetFragment.show(
                requireActivity().supportFragmentManager,
                bottomSheetFragment.tag
            )
            mypageViewModel.scrapFilterOnClickFalse()
        }
    }
```

필터 클릭 리스너를 통해 만들어놓은 bottomSheetFragment 생성

```
private fun setWriteOnCheckedListener(checkId: Int) {
        when (checkId) {
            R.id.chip_write_1 -> category = binding.chipWrite1.text.toString()
            R.id.chip_write_2 -> category = binding.chipWrite2.text.toString()
            R.id.chip_write_3 -> category = binding.chipWrite3.text.toString()
            R.id.chip_write_4 -> category = binding.chipWrite4.text.toString()
            R.id.chip_write_5 -> category = binding.chipWrite5.text.toString()
            R.id.chip_write_6 -> category = binding.chipWrite6.text.toString()
            R.id.chip_write_7 -> category = binding.chipWrite7.text.toString()
            R.id.chip_write_8 -> category = binding.chipWrite8.text.toString()
        }
    }
```

바텀 시트의 칩 구성이 변경될경우 fragment내의 값을 변경

```
private fun applyFilter() {
        if (category != "" || range != "") {
            mypageViewModel.setWriteFilter(range, category)
        }
    }
```

이후 값이 변경된 값을 다음과 같이 라이브 데이터로 구성된 뷰모델의 필터 값에 넣음

```
private val _mywriteFilter=MutableLiveData<MyWriteFilter>()
    val mywriteFilter: LiveData<MyWriteFilter>
        get()=_mywriteFilter

    fun setWriteFilter(range:String,category:String){
        val myfilter=MyWriteFilter(range,category)
        _mywriteFilter.value=myfilter
    }
```

라이브데이터로 구성된 필터에서 값이변경되는 것을 관찰하기 위해 바텀 시트를 사용한 fragment에서

```
mypageViewModel.mywriteFilter.observe(viewLifecycleOwner) {
            getSheetDataListener(it)
        }
```

다음과 같이 observe를 구성하여 값이 바뀌면 바로 동작하도록 설정