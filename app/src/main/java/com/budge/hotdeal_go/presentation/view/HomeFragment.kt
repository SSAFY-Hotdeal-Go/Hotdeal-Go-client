package com.budge.hotdeal_go.presentation.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import com.budge.hotdeal_go.R
import com.budge.hotdeal_go.databinding.FragmentHomeBinding
import com.budge.hotdeal_go.presentation.adapter.HomeNoticeRecyclerViewAdapter
import com.budge.hotdeal_go.presentation.adapter.HotDealRecyclerViewAdapter
import com.budge.hotdeal_go.presentation.base.BaseFragment
import com.budge.hotdeal_go.presentation.decoration.VerticalItemDecorator
import com.budge.hotdeal_go.presentation.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

private const val TAG = "HomeFragment_싸피"

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(
    FragmentHomeBinding::inflate,
    R.layout.fragment_home
) {

    private val viewModel by viewModels<HomeViewModel>()
    private lateinit var noticeListAdapter: HomeNoticeRecyclerViewAdapter
    private lateinit var latestHotDealListAdapter: HotDealRecyclerViewAdapter
    private lateinit var likeTop3ListAdapter: HotDealRecyclerViewAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setObserve()

        binding.noticeListRenewBtn.setOnClickListener {
            viewModel.renewNotice()
        }

        binding.likeTop3RenewBtn.setOnClickListener {
            viewModel.renewLikeTop3()
        }

        binding.keywordRenewBtn.setOnClickListener {
            viewModel.renewLatestHotdeal()
        }

        setRecyclerView()
    }

    private fun setObserve() {
        viewModel.renewNotice()
        viewModel.noticeItemList.observe(viewLifecycleOwner) {
            Log.d(TAG, "setObserve: ${it}")
            noticeListAdapter.submitList(it)
        }

        viewModel.renewLatestHotdeal()
        viewModel.latestHotDealItemList.observe(viewLifecycleOwner) {
            latestHotDealListAdapter.submitList(it)
        }

        viewModel.renewLikeTop3()
        viewModel.likeTop3ItemList.observe(viewLifecycleOwner) {
            Log.d(TAG, "setObserve: like: $it")
            likeTop3ListAdapter.submitList(it)
        }
    }

    private fun setRecyclerView() {
        binding.homeNoticeRecyclerView.addItemDecoration(VerticalItemDecorator(4))
        binding.homeLikeTopRecyclerView.addItemDecoration(VerticalItemDecorator(4))
        binding.homeKeywordRecyclerView.addItemDecoration(VerticalItemDecorator(4))

        noticeListAdapter = HomeNoticeRecyclerViewAdapter(
            itemClick = { noticeItem ->

            }
        )

        likeTop3ListAdapter = HotDealRecyclerViewAdapter(
            itemClick = { hotDealItem ->
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(hotDealItem.url)))
            }
        )

        latestHotDealListAdapter = HotDealRecyclerViewAdapter(
            itemClick = { hotDealItem ->
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(hotDealItem.url)))
            }
        )

        binding.homeNoticeRecyclerView.adapter = noticeListAdapter
        binding.homeLikeTopRecyclerView.adapter = likeTop3ListAdapter
        binding.homeKeywordRecyclerView.adapter = latestHotDealListAdapter
    }

}
