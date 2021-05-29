package com.teambeme.beme.idsearchfollowing.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.teambeme.beme.R
import com.teambeme.beme.databinding.ItemRecentSearchBinding
import com.teambeme.beme.idsearchfollowing.model.ResponseRecentSearchRecord

class RecentSearchAdapter(
    private val profileButtonEvent: ProfileButton
) : RecyclerView.Adapter<RecentSearchAdapter.RecentSearchViewHolder>() {
    private var recentSearchData = mutableListOf<ResponseRecentSearchRecord.Data>()

    class RecentSearchViewHolder(private val binding: ItemRecentSearchBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val btnDeleteRecentRecearch = binding.btnDeleteRecentSearch
        val recentSearchProfilePic = binding.recentSearchProfilePic

        fun bind(recentSearchData: ResponseRecentSearchRecord.Data) {
            with(binding) {
                recentSearch = recentSearchData
                executePendingBindings()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecentSearchViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: ItemRecentSearchBinding =
            DataBindingUtil.inflate(inflater, R.layout.item_recent_search, parent, false)
        return RecentSearchViewHolder(binding)
    }

    override fun getItemCount() = recentSearchData.size

    override fun onBindViewHolder(holder: RecentSearchViewHolder, position: Int) {
        with(holder) {
            bind(recentSearchData[position])
            recentSearchProfilePic.setOnClickListener { view ->
                profileButtonEvent.setOnPicClickListener(recentSearchData[position].id)
//                val intent = Intent(view.context, OtherPageActivity::class.java)
//                intent.putExtra("userId", recentSearchData[position].id)
//                Log.d("Internt", position.toString())
//                Log.d("Internt", recentSearchData[position].id.toString())
//                view.context.startActivity(intent)
            }
            btnDeleteRecentRecearch.setOnClickListener {
                profileButtonEvent.setDeleteClickListener(position)
//                 idSearchViewModel.setPosition(position)
                recentSearchData.removeAt(position)
                notifyDataSetChanged()
            }
        }
    }

    fun replaceList(newList: MutableList<ResponseRecentSearchRecord.Data>) {
        recentSearchData = newList.toMutableList()
        notifyDataSetChanged()
    }

    interface ProfileButton {
        fun setOnPicClickListener(id: Int)
        fun setDeleteClickListener(position: Int)
    }
}
