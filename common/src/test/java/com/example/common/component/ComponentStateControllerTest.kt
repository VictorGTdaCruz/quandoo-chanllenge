package com.example.common.component

import android.content.Context
import android.view.View
import android.widget.FrameLayout
import androidx.test.core.app.ApplicationProvider
import kotlinx.android.synthetic.main.component_states.view.*
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class ComponentStateControllerTest {

    private lateinit var states: ComponentStateController
    private lateinit var contentView: View
    private lateinit var viewGroup: FrameLayout

    @Before
    fun setup() {
        val context: Context = ApplicationProvider.getApplicationContext()
        contentView = View(context)
        viewGroup = FrameLayout(ApplicationProvider.getApplicationContext())
        states = ComponentStateController(viewGroup, contentView)
    }

    @Test
    fun `Should hide all views except loading when loading`() {
        states.updateState(Resource(data = 0, status = Resource.Status.LOADING))
        assertEquals(View.GONE, viewGroup.stateError?.visibility)
        assertEquals(View.VISIBLE, viewGroup.stateLoading?.visibility)
        assertEquals(View.INVISIBLE, contentView.visibility)
    }

    @Test
    fun `Should hide all views except content when success`() {
        states.updateState(Resource(data = 0, status = Resource.Status.SUCCESS))
        assertEquals(View.GONE, viewGroup.stateError?.visibility)
        assertEquals(View.GONE, viewGroup.stateLoading?.visibility)
        assertEquals(View.VISIBLE, contentView.visibility)
    }

    @Test
    fun `Should hide all views except error when state is error`() {
        states.updateState(Resource(data = 0, status = Resource.Status.ERROR))
        assertEquals(View.VISIBLE, viewGroup.stateError?.visibility)
        assertEquals(View.GONE, viewGroup.stateLoading?.visibility)
        assertEquals(View.INVISIBLE, contentView.visibility)
    }
}