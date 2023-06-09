package com.example.common.adapters

import android.widget.FrameLayout
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ApplicationProvider
import com.example.common.R
import kotlinx.android.synthetic.main.test_layout.view.*
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class AbstractAdapterTest {

    private lateinit var adapter: GenericRecyclerAdapter<String>

    private lateinit var list: List<Int>

    @Before
    fun setup() {
        list = listOf()
        adapter = GenericRecyclerAdapter(
            listOf(""),
            { R.layout.test_layout },
            { list.size },
            { list.indices })
    }

    @Test
    fun `Should have one item`() {
        assertEquals(1, adapter.itemCount)
    }

    @Test
    fun `Should create view holder`() {
        val viewHolder = adapter.onCreateViewHolder(
            FrameLayout(ApplicationProvider.getApplicationContext()),
            R.layout.test_layout
        )
        assertNotNull(viewHolder.itemView.test_text)
    }

    @Test
    fun `Should click on view holder`() {
        val recyclerView = RecyclerView(ApplicationProvider.getApplicationContext())
        recyclerView.adapter = adapter
        val viewHolder = adapter.onCreateViewHolder(
            FrameLayout(ApplicationProvider.getApplicationContext()),
            R.layout.test_layout
        )
        adapter.onBindViewHolder(viewHolder, 0)
        viewHolder.itemView.performClick()
    }

    @Test
    fun `Should be test layout`() {
        assertEquals(R.layout.test_layout, adapter.getItemViewType(0))
    }
}