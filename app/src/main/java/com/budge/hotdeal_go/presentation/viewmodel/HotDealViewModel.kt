package com.budge.hotdeal_go.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.budge.hotdeal_go.data.model.HotDealItem
import com.budge.hotdeal_go.domain.usecase.GetFmkoreaHotdealItems
import com.budge.hotdeal_go.domain.usecase.GetQuasarzoneHotdealItems
import com.budge.hotdeal_go.domain.usecase.GetRuliwebHotdealItems
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HotDealViewModel @Inject constructor(
    private val getFmkoreaHotdelItems: GetFmkoreaHotdealItems,
    private val getQuasarzoneHotdealItems: GetQuasarzoneHotdealItems,
    private val getRuliwebHotdealItems: GetRuliwebHotdealItems
) : ViewModel() {

    private val _hotDealItemList = MutableLiveData<List<HotDealItem>>(emptyList())
    val hotDealItemList: LiveData<List<HotDealItem>> get() = _hotDealItemList

    private fun getFmKoreaHotdeal() =
        viewModelScope.async {
            runCatching {
                getFmkoreaHotdelItems.getItems()
            }.getOrDefault(emptyList())
        }


    private fun getQuasarzoneHotdeal() =
        viewModelScope.async {
            runCatching {
                getQuasarzoneHotdealItems.getItems()
            }.getOrDefault(emptyList())
        }


    private fun getRuliwebHotdeal() = viewModelScope.async {
        runCatching {
            getRuliwebHotdealItems.getItems()
        }.getOrDefault(emptyList())
    }


    fun searchItem(booleanArray: BooleanArray) {

        val newHotDealItemList = mutableListOf<HotDealItem>()
        viewModelScope.launch {
            booleanArray.forEachIndexed { index, boolean ->
                if (boolean) {
                    when (index) {
                        0 -> newHotDealItemList.addAll(getFmKoreaHotdeal().await())
                        1 -> newHotDealItemList.addAll(getQuasarzoneHotdeal().await())
                        2 -> newHotDealItemList.addAll(getRuliwebHotdeal().await())
                    }
                }
            }
        }

        _hotDealItemList.value = newHotDealItemList.sortedBy { it.time }
    }


}
