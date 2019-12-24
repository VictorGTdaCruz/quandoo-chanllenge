package com.example.common.adapters

import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.test.core.app.ApplicationProvider
import com.example.common.R
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Assert.assertFalse
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class GenericPagerAdapterTest {

    private lateinit var adapter: GenericPagerAdapter
    private lateinit var viewGroup: ConstraintLayout

    @Before
    fun setup() {
        val item = PagerItem(R.layout.test_layout) { }
        adapter = GenericPagerAdapter(ApplicationProvider.getApplicationContext(), listOf(item))
        viewGroup = ConstraintLayout(ApplicationProvider.getApplicationContext())
    }

    @Test
    fun `Should instantiate item`() {
        val layout = adapter.instantiateItem(viewGroup, 0)
        assertEquals(layout, viewGroup.getChildAt(0))
    }

    @Test
    fun `Should destroy item`() {
        val layout = adapter.instantiateItem(viewGroup, 0)
        assertEquals(1, viewGroup.childCount)
        adapter.destroyItem(viewGroup, 0, layout)
        assertEquals(0, viewGroup.childCount)
    }

    @Test
    fun `Should return true when view is from object`() {
        assertTrue(adapter.isViewFromObject(viewGroup, viewGroup))
    }

    @Test
    fun `Should return false when view is not from object`() {
        assertFalse(
            adapter.isViewFromObject(
                viewGroup,
                View(ApplicationProvider.getApplicationContext())
            )
        )
    }

    @Test
    fun `Should return 1 when adapter count is called`() {
        assertEquals(1, adapter.count)
    }
}