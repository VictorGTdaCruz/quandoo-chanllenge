package com.example.feature_merchants.list.component

import android.content.Context
import android.util.AttributeSet
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.example.common.component.ComponentStateController
import com.example.domain.models.Merchant
import com.example.domain.models.MerchantPagedList
import com.example.feature_merchants.R
import com.example.feature_merchants.di.viewModel
import com.example.common.component.LifecycleOwnerConstraintLayout
import com.example.common.component.Resource.Status.SUCCESS
import com.example.feature_merchants.di.selfInject
import kotlinx.android.synthetic.main.item_merchants_list.view.*
import kotlinx.android.synthetic.main.widget_merchants_list.view.*
import org.kodein.di.KodeinAware

class MerchantListView: KodeinAware, LifecycleOwnerConstraintLayout {

    override val kodein = selfInject()

    private val viewModel by viewModel<MerchantListViewModel>()

    private val componentState: ComponentStateController

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    companion object {
        private const val NUMBER_OF_COLUMNS = 3
    }

    init {
        inflate(context, R.layout.widget_merchants_list, this)
        componentState = ComponentStateController(this, contentView = merchantsList)

        if (!isInEditMode) {
            viewModel.merchantsList.observe(this, Observer {
                componentState.updateState(it)
                if (it.status == SUCCESS) updateList(it.data ?: MerchantPagedList())
            })

            viewModel.loadNextPage()
        }

        setupList()
    }

    private fun setupList() {
        merchantsList.run {
            adapter = GenericAdapter<Merchant>(
                items = emptyList(),
                viewType = { R.layout.item_merchants_list },
                click = {
                    // TODO go to details screen
                },
                bindHolder = {
                    merchantTitle.text = it.name
                    Glide.with(context).load(it.images?.firstOrNull()?.url)
                            .error(R.drawable.ic_restaurant_black_24dp)
                            .into(merchantImage)
                }
            )
            layoutManager = GridLayoutManager(context, NUMBER_OF_COLUMNS)
        }
    }

    private fun updateList(merchantPagedList: MerchantPagedList) {
        (merchantsList.adapter as GenericAdapter<Merchant>).run {
            items = merchantPagedList.merchants ?: emptyList()
            notifyDataSetChanged()
        }
    }
}