# RecyclerView + SnapHelper
- RecyclerView의 스크롤이 이어지지 않고 뷰페이저처럼 끊겨서 Snap되는 것

```
private fun setSnapHelper(binding: FragmentExploreBinding) {
    val snapHelper = PagerSnapHelper()
    snapHelper.attachToRecyclerView(binding.rcvExploreOtherminds)
}
```