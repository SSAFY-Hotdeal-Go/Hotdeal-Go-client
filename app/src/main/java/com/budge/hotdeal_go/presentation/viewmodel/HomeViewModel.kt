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
        viewModelScope.launch {
            _noticeItemList.value = runCatching {
                getNoticeListItems.getItems()
            }.getOrDefault(emptyList()).sortedBy { it.time }.reversed()
        }

    private fun getLatestHotDealItem() =
        viewModelScope.launch {
            _latestHotDealItemList.value = runCatching {
                getLatestHotDealItems.getItems()
            }.getOrDefault(emptyList()).sortedBy { it.time }.reversed()
        }

    private fun getLikeTop3Item() =
        viewModelScope.launch {
            _likeTop3ItemList.value = runCatching {
                getLikeTop3Items.getItems()
            }.getOrDefault(emptyList()).sortedBy { it.likeCnt }.reversed()
        }


    fun renewNotice() {
        getNotice()
    }

    fun renewLatestHotdeal() {
        getLatestHotDealItem()
    }

    fun renewLikeTop3() {
        getLikeTop3Item()
    }

}
