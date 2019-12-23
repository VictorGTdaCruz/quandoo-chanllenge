package com.example.feature_merchants.list.component

import androidx.recyclerview.widget.RecyclerView
import com.example.common.adapters.GenericRecyclerAdapter
import com.example.domain.models.Merchant


class PagedRecyclerManager(
    private val recyclerView: RecyclerView,
    var pageToLoad: Int = 0,
    private val shouldLoadMore: (pageToLoad: Int) -> Unit)
{
    var lastPageReached = false
    var isLoading = true
        set(value) {
            val adapter = recyclerView.adapter as GenericRecyclerAdapter<Merchant>
            if (value) addLoading(adapter) else removeLoading(adapter)
            field = value
        }

    init {
        recyclerView.addOnScrollListener(
            object: RecyclerView.OnScrollListener() {
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)

                    if (!recyclerView.canScrollVertically(DOWN)) {
                        if (!lastPageReached && !isLoading) {
                            isLoading = true
                            pageToLoad++
                            shouldLoadMore(pageToLoad)
                        }
                    }
                }
            }
        )
    }

    fun shouldShowLoading(position: Int): Boolean {
        return !lastPageReached
                && position == recyclerView.adapter?.itemCount?.minus(1)
                && isLoading
    }

    fun updateItems(itemsToAdd: List<Merchant>,
                    abstractAdapter: GenericRecyclerAdapter<Merchant>,
                    isLastPage: Boolean = false) {
        lastPageReached = isLastPage
        isLoading = false

        abstractAdapter.items = itemsToAdd.toList()
        abstractAdapter.notifyDataSetChanged()
    }

    private fun addLoading(adapter: GenericRecyclerAdapter<Merchant>) {
        val currentList = adapter.items.toMutableList()
        val newItems = currentList.apply { add(currentList.first()) }

        adapter.items = newItems
        adapter.notifyDataSetChanged()
        recyclerView.smoothScrollToPosition(newItems.size - 1)
    }

    private fun removeLoading(adapter: GenericRecyclerAdapter<Merchant>) {
        if (adapter.items.isEmpty()) return
        val loadingPosition = adapter.itemCount - 1
        val currentList = adapter.items.toMutableList()
        val newItems = currentList.apply { removeAt(loadingPosition) }

        adapter.items = newItems
        adapter.notifyDataSetChanged()
    }

    companion object {
        private const val DOWN = 1
    }
}