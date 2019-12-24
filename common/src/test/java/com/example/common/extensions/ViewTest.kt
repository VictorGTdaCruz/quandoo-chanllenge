package com.example.common.extensions

import android.view.View
import androidx.test.core.app.ApplicationProvider
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class ViewTest {

    private val view = View(ApplicationProvider.getApplicationContext())

    @Test
    fun `Should show view`() {
        view.show()
        assertEquals(View.VISIBLE, view.visibility)
    }

    @Test
    fun `Should hide view`() {
        view.hide()
        assertEquals(View.GONE, view.visibility)
    }

    @Test
    fun `Should make view invisible`() {
        view.invisible()
        assertEquals(View.INVISIBLE, view.visibility)
    }
}