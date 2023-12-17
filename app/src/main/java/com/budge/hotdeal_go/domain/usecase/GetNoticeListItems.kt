package com.budge.hotdeal_go.domain.usecase

import com.budge.hotdeal_go.data.model.NoticeItem
import com.budge.hotdeal_go.domain.repository.HomeRepository
import javax.inject.Inject

class GetNoticeListItems @Inject constructor(
    private val homeRepository: HomeRepository
) {
    //    suspend fun getItems() = homeRepository.getNoticeList()

    private val list = List(5) { idx ->
        NoticeItem(idx, "제목$idx", "공지 내용$idx", "2023-12-13 09:53:22")
    }

    suspend fun getItems(): List<NoticeItem> {
        return list
    }
}
