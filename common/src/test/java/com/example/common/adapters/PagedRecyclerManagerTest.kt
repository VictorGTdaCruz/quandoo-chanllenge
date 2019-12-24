package com.example.common.adapters

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ApplicationProvider
import com.example.common.R
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
@Suppress("UNCHECKED_CAST")
class PagedRecyclerManagerTest {

    lateinit var manager: PagedRecyclerManager
    lateinit var recyclerView: RecyclerView

    @Before
    fun setup() {
        recyclerView = RecyclerView(ApplicationProvider.getApplicationContext())
        val adapter = GenericRecyclerAdapter(mutableListOf("1", "2"), { R.layout.test_layout })
        recyclerView.adapter = adapter
        manager = PagedRecyclerManager(recyclerView, 0) {}
    }

    @Test
    fun `Should show loading be true when on last position and loading`() {
            manager.isLoading = true
        assertTrue(manager.shouldShowLoading(2))
    }

    @Test
    fun `Should not show loading be false when on last position and not loading`() {
        manager.isLoading = false
        assertFalse(manager.shouldShowLoading(2))
    }

    @Test
    fun `Should not show loading on last page`() {
        manager.isLoading = true
        manager.lastPageReached = true
        assertFalse(manager.shouldShowLoading(2))
    }

    @Test
    fun `Should update items`() {
        val adapter = recyclerView.adapter as GenericRecyclerAdapter<String>
        adapter.items = listOf()
        val expected = "test"
        manager.updateItems(listOf(expected), adapter)
        assertEquals(expected, adapter.items.first())
    }
}