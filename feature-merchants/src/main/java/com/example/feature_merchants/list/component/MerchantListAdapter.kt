package com.example.feature_merchants.list.component

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.models.Merchant

class MerchantListAdapter(
    var items: List<Merchant> = emptyList(),
    private val viewType: (Int) -> Int,
    private val click: Merchant.(View) -> Unit = {},
    private val bindHolder: View.(Merchant) -> Unit = {}
) : RecyclerView.Adapter<Holder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context).inflate(viewType, parent, false)
        val viewHolder = Holder(view)
        val itemView = viewHolder.itemView
        itemView.setOnClickListener {
            val adapterPosition = viewHolder.adapterPosition
            if (adapterPosition != RecyclerView.NO_POSITION) {
                items[adapterPosition].click(itemView)
            }
        }
        return viewHolder
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.itemView.bindHolder(items[position])
    }

    override fun getItemViewType(position: Int): Int {
        return viewType(position)
    }
}

class Holder(itemView: View): RecyclerView.ViewHolder(itemView)