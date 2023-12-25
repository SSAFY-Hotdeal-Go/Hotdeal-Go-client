package com.budge.hotdeal_go.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.budge.hotdeal_go.data.model.HotDealItem
import com.budge.hotdeal_go.domain.usecase.GetHotdealItems
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HotDealViewModel @Inject constructor(
    private val getHotdealItems: GetHotdealItems,
) : ViewModel() {
    private val _hotDealItemList = MutableLiveData<List<HotDealItem>>(emptyList())
    val hotDealItemList: LiveData<List<HotDealItem>> get() = _hotDealItemList

    val isFmkoreaChecked = MutableLiveData(true)

    val isQuasarzoneChecked = MutableLiveData(true)

    val isRuliwebChecked = MutableLiveData(true)

    private suspend fun getHotdeal(searchString: String = "", sitenoString: String = "0") {
        var newHotDealItemList = runCatching {
            getHotdealItems.getItems(searchString, sitenoString)
        }.getOrDefault(emptyList())
        if (sitenoString == "") newHotDealItemList = emptyList()
        _hotDealItemList.value = newHotDealItemList
    }


    fun searchItem(searchString: String = "") {
        var sitenoString = ""
        viewModelScope.launch {
            if (isFmkoreaChecked.value == true) sitenoString += "1,"
            if (isRuliwebChecked.value == true) sitenoString += "2,"
            if (isQuasarzoneChecked.value == true) sitenoString += "3,"
            if (sitenoString == "1,2,3,") sitenoString = "0"

            getHotdeal(searchString = searchString, sitenoString = sitenoString)

        }
    }

}
