package com.budge.hotdeal_go.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.budge.hotdeal_go.R
import com.budge.hotdeal_go.data.model.HotDealItem
import com.budge.hotdeal_go.databinding.ItemHotdealBinding
import com.bumptech.glide.Glide
import java.text.SimpleDateFormat
import java.util.Locale

class HotDealRecyclerViewAdapter(
    private val itemClick: (HotDealItem) -> (Unit)
) : ListAdapter<HotDealItem, HotDealRecyclerViewAdapter.HotDealViewHolder>(diffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HotDealViewHolder {
        val binding = ItemHotdealBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HotDealViewHolder(binding, itemClick)
    }

    override fun onBindViewHolder(holder: HotDealViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun submitList(list: List<HotDealItem>?) {
        super.submitList(list)
    }


    inner class HotDealViewHolder(
        private val binding: ItemHotdealBinding,
        private val itemClick: (HotDealItem) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        lateinit var hotDealItem: HotDealItem

        init {
            itemView.setOnClickListener { itemClick(hotDealItem) }
        }

        fun bind(hotDealItem: HotDealItem) {
            this.hotDealItem = hotDealItem
            val simpleDateParser = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.KOREAN)
            val simpleDateFormatter = SimpleDateFormat("MM-dd HH:mm")
            binding.hotDealTitle.text = hotDealItem.title ?: "정보없음"
            binding.hotDealShoppingMall.text = hotDealItem.purchasingPlace ?: "정보없음"
            binding.hotDealCost.text = hotDealItem.price ?: "정보없음"
            binding.hotDealLikeCnt.text = hotDealItem.likeCnt?.toString() ?: "0"
            binding.hotDealDeliveryFee.text = hotDealItem.shippingFee ?: "정보없음"
            binding.hotDealWriteDate.text =
                simpleDateFormatter.format(simpleDateParser.parse(hotDealItem.time)) ?: "정보없음"
            Glide.with(itemView)
                .load(hotDealItem.img)
                .placeholder(R.drawable.baseline_add_box_24)
                .into(binding.hotDealImg)
        }
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<HotDealItem>() {
            override fun areItemsTheSame(oldItem: HotDealItem, newItem: HotDealItem): Boolean {
                return oldItem.title == newItem.title
            }

            override fun areContentsTheSame(oldItem: HotDealItem, newItem: HotDealItem): Boolean {
                return oldItem == newItem
            }

        }
    }
}
