package com.budge.hotdeal_go.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.budge.hotdeal_go.data.model.HotDealItem
import com.budge.hotdeal_go.domain.usecase.GetHotdealItems
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
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

    private fun getHotdeal(searchString: String? = "", siteno: String? = "0") =
        viewModelScope.async {
            runCatching {
                getHotdealItems.getItems(searchString, siteno)
            }.getOrDefault(emptyList())
        }


    fun searchItem(searchString: String? = "") {
        val newHotDealItemList = mutableListOf<HotDealItem>()
        var sitenoString = ""
        viewModelScope.launch {
            if (isFmkoreaChecked.value == true) sitenoString += "1,"
            if (isRuliwebChecked.value == true) sitenoString += "2,"
            if (isQuasarzoneChecked.value == true) sitenoString += "3,"
            if (sitenoString == "") sitenoString = "-1"
            if (sitenoString == "1,2,3,") sitenoString = "0"

            newHotDealItemList.addAll(
                getHotdeal(
                    searchString = if (searchString != "") searchString else null,
                    siteno = sitenoString
                ).await()
            )

            _hotDealItemList.value = newHotDealItemList.sortedBy { it.time }.reversed()
        }
    }

}
