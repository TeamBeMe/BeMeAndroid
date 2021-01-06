# 맨 밑까지 스크롤시 더 보기 버튼을 띄워봅시다
## 스크롤시 이벤트를 처리해주는 OnScrollListener()
```
addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    val lastVisiblePosition =
                        (layoutManager as LinearLayoutManager).findLastVisibleItemPosition()
                    val itemTotalCount = adapter!!.itemCount - 1
                    if (lastVisiblePosition == itemTotalCount) {
                        binding.btnScrapShowmore.visibility = View.VISIBLE
                    } else {
                        binding.btnScrapShowmore.visibility = View.GONE
                    }
                    super.onScrolled(recyclerView, dx, dy)
                }
            })
```

ScrollListener를 정의하여 값이 맨 밑에 올 경우 버튼을 visible로 바꾸고 스크롤이 다시 위로 가면 gone으로 바꿔서 버튼이 안보이게 구현