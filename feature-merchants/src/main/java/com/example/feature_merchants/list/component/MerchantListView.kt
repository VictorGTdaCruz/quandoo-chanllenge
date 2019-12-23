package com.example.feature_merchants.list.component

import android.content.Context
import android.util.AttributeSet
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.example.common.adapters.GenericRecyclerAdapter
import com.example.common.adapters.PagedRecyclerManager
import com.example.common.component.ComponentStateController
import com.example.domain.models.Merchant
import com.example.feature_merchants.R
import com.example.common.component.LifecycleOwnerConstraintLayout
import com.example.common.component.Resource.Status.SUCCESS
import com.example.common.selfInject
import com.example.common.viewModel
import com.example.domain.models.MerchantPagedList
import com.example.feature_merchants.util.MerchantMapper
import com.example.feature_merchants.dto.ParcelableMerchantDTO
import kotlinx.android.synthetic.main.item_merchants_list.view.*
import kotlinx.android.synthetic.main.widget_merchants_list.view.*
import org.kodein.di.KodeinAware

class MerchantListView: KodeinAware, LifecycleOwnerConstraintLayout {

    override val kodein = selfInject()
    private val viewModel by viewModel<MerchantListViewModel>()

    private val componentState: ComponentStateController
    private var pagedRecyclerManager: PagedRecyclerManager
    var onItemClick: (ParcelableMerchantDTO) -> Unit = {}

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    companion object {
        private const val NUMBER_OF_COLUMNS = 3
        private const val NUMBER_OF_REQUESTS_ON_INIT = 4
    }

    init {
        inflate(context, R.layout.widget_merchants_list, this)
        componentState = ComponentStateController(this, contentView = merchantsList)

        if (!isInEditMode) {
            viewModel.merchantsList.observe(this, Observer {
                componentState.updateState(it)
                if (it.status == SUCCESS) updateList(it.data)
            })

            loadInitialRequests()
        }

        pagedRecyclerManager = PagedRecyclerManager(
            recyclerView = merchantsList,
            pageToLoad = NUMBER_OF_REQUESTS_ON_INIT - 1
        ) {
            viewModel.loadPage(it)
        }

        setupList()
    }

    private fun loadInitialRequests() {
        if (viewModel.merchantsList.value == null)
            for (i in 0.until(NUMBER_OF_REQUESTS_ON_INIT))
                viewModel.loadPage(i)
    }

    private fun setupList() {
        merchantsList.run {
            adapter = GenericRecyclerAdapter<Merchant>(
                items = emptyList(),
                viewType = {
                    if (pagedRecyclerManager.shouldShowLoading(it))
                        R.layout.item_loading
                    else
                        R.layout.item_merchants_list
                },
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
            layoutManager = GridLayoutManager(context, NUMBER_OF_COLUMNS)
        }
    }

    private fun updateList(merchantPagedList: MerchantPagedList) {
        pagedRecyclerManager.updateItems(
            itemsToAdd = merchantPagedList.merchants,
            abstractAdapter = merchantsList.adapter as GenericRecyclerAdapter<Merchant>,
            isLastPage = viewModel.isLastPage()
        )
    }
}