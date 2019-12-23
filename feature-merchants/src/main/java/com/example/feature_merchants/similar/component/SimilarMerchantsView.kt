package com.example.feature_merchants.similar.component

import android.content.Context
import android.util.AttributeSet
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.example.common.adapters.GenericRecyclerAdapter
import com.example.common.component.ComponentStateController
import com.example.common.component.LifecycleOwnerConstraintLayout
import com.example.common.component.Resource
import com.example.common.selfInject
import com.example.common.viewModel
import com.example.domain.models.Merchant
import com.example.feature_merchants.R
import com.example.feature_merchants.dto.ParcelableMerchantDTO
import com.example.feature_merchants.util.MerchantMapper
import kotlinx.android.synthetic.main.item_merchants_list.view.*
import kotlinx.android.synthetic.main.widget_similar_merchants.view.*
import org.kodein.di.KodeinAware


class SimilarMerchantsView : KodeinAware, LifecycleOwnerConstraintLayout {

    override val kodein = selfInject()
    private val viewModel by viewModel<SimilarMerchantsViewModel>()

    private val componentState: ComponentStateController
    var onItemClick: (ParcelableMerchantDTO) -> Unit = {}

    var merchantId: Int? = null
        set(value) {
            field = value
            viewModel.loadSimilarMerchants(value ?: 0)
        }

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    init {
        inflate(context, R.layout.widget_similar_merchants, this)
        componentState = ComponentStateController(this, contentView = similarMerchantsRecycler)

        if (!isInEditMode) {
            viewModel.merchantsList.observe(this, Observer {
                componentState.updateState(it)
                if (it.status == Resource.Status.SUCCESS) updateList(it.data)
            })
        }

        setupList()
    }

    private fun setupList() {
        similarMerchantsRecycler.run {
            adapter = GenericRecyclerAdapter<Merchant>(
                items = emptyList(),
                viewType = { R.layout.item_merchants_list },
                click = {
                    onItemClick(MerchantMapper.mapMerchantToParcelableMerchantDTO(it))
                },
                bindHolder = {
                    val progress = CircularProgressDrawable(context)
                    progress.start()
                    merchantTitle?.text = it.name
                    merchantImage?.let { view ->
                        Glide.with(context).load(it.images?.firstOrNull()?.url)
                            .placeholder(progress)
                            .error(R.drawable.ic_restaurant_black_24dp)
                            .into(view)
                    }
                }
            )
            layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        }
    }

    private fun updateList(merchantList: List<Merchant>) {
        (similarMerchantsRecycler.adapter as GenericRecyclerAdapter<Merchant>).run {
            items = merchantList
            notifyDataSetChanged()
        }
    }
}