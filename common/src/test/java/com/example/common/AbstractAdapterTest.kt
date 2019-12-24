package com.example.common

import android.view.View
import android.widget.FrameLayout
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ApplicationProvider
import com.example.common.adapters.GenericRecyclerAdapter
import com.example.common.adapters.Holder
import kotlinx.android.synthetic.main.test_layout.view.*
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class AbstractAdapterTest {

    private lateinit var adapter: GenericRecyclerAdapter<String>

    private lateinit var mockObject: List<*>

    @Before
    fun setup() {
        mockObject = Mockito.mock(List::class.java)
        adapter = GenericRecyclerAdapter(
            listOf(""),
            { R.layout.test_layout },
            { mockObject.size },
            { mockObject.indices })
    }

    @Test
    fun testGetItemCount() {
        assertEquals(1, adapter.itemCount)
    }

    @Test
    fun testOnCreateViewHolder() {
        val viewHolder = adapter.onCreateViewHolder(
            FrameLayout(ApplicationProvider.getApplicationContext()),
            R.layout.test_layout)
        assertNotNull(viewHolder.itemView.test_text)
    }

    @Test
    fun testOnBindViewHolder() {
        adapter.onBindViewHolder(Holder(View(ApplicationProvider.getApplicationContext())), 0)
        Mockito.verify(mockObject).size
    }

    @Test
    fun testOnItemClick() {
        val recyclerView = RecyclerView(ApplicationProvider.getApplicationContext())
        recyclerView.adapter = adapter
        val viewHolder = adapter.onCreateViewHolder(
            FrameLayout(ApplicationProvider.getApplicationContext()), R.layout.test_layout)
        adapter.onBindViewHolder(viewHolder, 0)
        viewHolder.itemView.performClick()
    }

    @Test
    fun testGetItemViewType() {
        assertEquals(R.layout.test_layout, adapter.getItemViewType(0))
    }
}