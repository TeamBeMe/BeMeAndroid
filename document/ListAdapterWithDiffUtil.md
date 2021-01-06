# DiffUtil를 적용한 ListAdapter
## notifyDataSetChanged()
- Adapter에게 RecyclerView의 리스트 데이터가 바뀌었으니 모든 항목을 **통째로** 업데이트를 하라는 신호를 보냄

## DiffUtil
- 그런데 실질적으로 다시 그려야하는 item이 1개 뿐이라해도 Adapter는 그 사실을 알지 못하므로 전체 item을 모두 업데이트 하게 된다
- 이런 불필요한 교체 비용을 줄이기 위해 DiffUtil을 사용했다

  ```kotlin
  class OtherquestionsRcvAdapter :
      ListAdapter<OtherquestionsData, OtherquestionsRcvAdapter.OtherquestionsRcvViewHolder>(
          OtherquestionsDiffUtil()
      ) {

        ...

      private class OtherquestionsDiffUtil : DiffUtil.ItemCallback<OtherquestionsData>() {
          override fun areItemsTheSame(oldItem: OtherquestionsData, newItem: OtherquestionsData) =
              (oldItem.title == newItem.title)

          override fun areContentsTheSame(oldItem: OtherquestionsData, newItem: OtherquestionsData) =
              (oldItem == newItem)
      }
  }
  ```

- RecyclerView에 SnapHelper를 적용 : RecyclerView의 스크롤이 이어지지 않고 뷰페이저처럼 끊겨서 snap되는 것
