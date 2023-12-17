package com.budge.hotdeal_go.presentation.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.budge.hotdeal_go.data.model.NoticeItem
import com.budge.hotdeal_go.databinding.ItemNoticeBinding
import java.text.SimpleDateFormat
import java.util.Locale

private const val TAG = "HomeNoticeAdapter_싸피"

class HomeNoticeRecyclerViewAdapter(
    private val itemClick: (NoticeItem) -> (Unit)
) : ListAdapter<NoticeItem, HomeNoticeRecyclerViewAdapter.HomeNoticeViewHolder>(diffUtil) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HomeNoticeRecyclerViewAdapter.HomeNoticeViewHolder {
        val binding = ItemNoticeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HomeNoticeViewHolder(binding, itemClick)
    }

    override fun onBindViewHolder(holder: HomeNoticeViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun submitList(list: List<NoticeItem>?) {
        super.submitList(list)
    }

    inner class HomeNoticeViewHolder(
        private val binding: ItemNoticeBinding,
        private val itemClick: (NoticeItem) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        lateinit var noticeItem: NoticeItem

        init {
            Log.d(TAG, "init: 어댑터...")
            itemView.setOnClickListener { itemClick(noticeItem) }
        }

        fun bind(noticeItem: NoticeItem) {
            Log.d(TAG, "bind: 바인드는 되냐...")
            this.noticeItem = noticeItem
            val simpleDateParser = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.KOREAN)
            val simpleDateFormatter = SimpleDateFormat("MM-dd HH:mm")
            binding.noticeTitleTV.text = noticeItem.title ?: "정보없음"
            binding.noticeContentTV.text = noticeItem.content ?: "정보없음"
            binding.noticeTimeTV.text =
                simpleDateFormatter.format(simpleDateParser.parse(noticeItem.time)) ?: "정보없음"
        }
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<NoticeItem>() {
            override fun areItemsTheSame(oldItem: NoticeItem, newItem: NoticeItem): Boolean {
                return oldItem.title == newItem.title
            }

            override fun areContentsTheSame(oldItem: NoticeItem, newItem: NoticeItem): Boolean {
                return oldItem == newItem
            }

        }
    }
}
