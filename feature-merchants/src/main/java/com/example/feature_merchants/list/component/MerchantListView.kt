package com.example.feature_merchants.list.component

import android.content.Context
import android.util.AttributeSet
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.domain.models.MerchantPagedList
import com.example.feature_merchants.R
import com.example.feature_merchants.di.selfInject
import com.example.feature_merchants.di.viewModel
import com.example.feature_merchants.list.LifecycleOwnerConstraintLayout
import kotlinx.android.synthetic.main.item_merchants_list.view.*
import kotlinx.android.synthetic.main.widget_merchants_list.view.*
import org.kodein.di.KodeinAware

class MerchantListView: KodeinAware, LifecycleOwnerConstraintLayout {

    override val kodein = selfInject()

    private val viewModel by viewModel<MerchantListViewModel>()

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    init {
        inflate(context, R.layout.widget_merchants_list, this)

        if (!isInEditMode) {
//            viewModel = ViewModelProviders.of(getActivity()).get(MerchantListViewModel::class.java)
            viewModel.merchantsList.observe(this, Observer {
                updateList(it)
            })
            viewModel.loadNextPage()
        }

        setupList()
    }

    private fun setupList() {
        merchantsList.run {
            adapter = MerchantListAdapter(
                items = emptyList(),
                viewType = { R.layout.item_merchants_list },
                click = { },
                bindHolder = {
                    merchantTitle.text = it.name
                }
            )
            layoutManager = LinearLayoutManager(context)
        }
    }

    private fun updateList(merchantPagedList: MerchantPagedList) {
        (merchantsList.adapter as MerchantListAdapter).run {
            items = merchantPagedList.merchants ?: emptyList()
            notifyDataSetChanged()
        }
    }
}