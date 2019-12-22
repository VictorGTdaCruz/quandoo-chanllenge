package com.example.common.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter

class GenericPagerAdapter(private val context: Context,
                          private val items: List<PagerItem>) : PagerAdapter() {

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val item = items[position]
        val inflater = LayoutInflater.from(context)
        val layout = inflater.inflate(item.layout, container, false)
        container.addView(layout)
        item.bindValues(layout)
        return layout
    }

    override fun destroyItem(container: ViewGroup, position: Int, view: Any) {
        container.removeView(view as View)
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun getCount() = items.size
}

data class PagerItem(
    val layout: Int,
    val bindValues: View.() -> Unit
)