package com.budge.hotdeal_go.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.budge.hotdeal_go.data.model.HotDealItem
import com.budge.hotdeal_go.data.model.NoticeItem
import com.budge.hotdeal_go.domain.usecase.GetLatestHotDealItems
import com.budge.hotdeal_go.domain.usecase.GetLikeTop3Items
import com.budge.hotdeal_go.domain.usecase.GetNoticeListItems
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getNoticeListItems: GetNoticeListItems,
    private val getLatestHotDealItems: GetLatestHotDealItems,
    private val getLikeTop3Items: GetLikeTop3Items
) : ViewModel() {
    private val _noticeItemList = MutableLiveData<List<NoticeItem>>(emptyList())
    val noticeItemList: LiveData<List<NoticeItem>> get() = _noticeItemList

    private val _latestHotDealItemList = MutableLiveData<List<HotDealItem>>(emptyList())
    val latestHotDealItemList: LiveData<List<HotDealItem>> get() = _latestHotDealItemList

    private val _likeTop3ItemList = MutableLiveData<List<HotDealItem>>(emptyList())
    val likeTop3ItemList: LiveData<List<HotDealItem>> get() = _likeTop3ItemList

    private fun getNotice() =
        viewModelScope.async {
            runCatching {
                getNoticeListItems.getItems()
            }.getOrDefault(emptyList<NoticeItem>())
        }

    private fun getLatestHotDealItem() =
        viewModelScope.async {
            runCatching {
                getLatestHotDealItems.getItems()
            }.getOrDefault(emptyList<HotDealItem>())
        }

    private fun getLikeTop3Item() =
        viewModelScope.async {
            runCatching {
                getLikeTop3Items.getItems()
            }.getOrDefault(emptyList<HotDealItem>())
        }


    fun renewNotice() {
        val newNoticeList = mutableListOf<NoticeItem>()
        viewModelScope.launch {
            newNoticeList.addAll(getNotice().await())
            _noticeItemList.value = newNoticeList.sortedBy { it.time }.reversed()
        }
    }

    fun renewLatestHotdeal() {
        val newLatestHotdealList = mutableListOf<HotDealItem>()
        viewModelScope.launch {
            newLatestHotdealList.addAll(getLatestHotDealItem().await() as Collection<HotDealItem>)
            _latestHotDealItemList.value = newLatestHotdealList.sortedBy { it.time }.reversed()
        }
    }

    fun renewLikeTop3() {
        val newLikeTop3List = mutableListOf<HotDealItem>()
        viewModelScope.launch {
            newLikeTop3List.addAll(getLikeTop3Item().await() as Collection<HotDealItem>)
            _likeTop3ItemList.value = newLikeTop3List.sortedBy { it.likeCnt }.reversed()
        }
    }

}
