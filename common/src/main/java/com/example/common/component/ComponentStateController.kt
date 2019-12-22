package com.example.common.component

import android.view.View
import android.view.ViewGroup
import com.example.common.R
import com.example.common.extensions.hide
import com.example.common.extensions.invisible
import com.example.common.extensions.show
import com.example.common.component.Resource.Status.LOADING
import com.example.common.component.Resource.Status.SUCCESS
import com.example.common.component.Resource.Status.ERROR
import kotlinx.android.synthetic.main.component_states.view.*

class ComponentStateController(viewGroup: ViewGroup, var contentView: View) {

    private val loadingLayout: View
    private val errorLayout: View

    init {
        View.inflate(viewGroup.context, R.layout.component_states, viewGroup)
        loadingLayout = viewGroup.stateLoading
        errorLayout = viewGroup.stateError
    }

    fun updateState(resource: Resource<*>) {
        when (resource.status) {
            LOADING -> onLoading()
            ERROR -> onError()
            SUCCESS -> onSuccess()
        }
    }

    private fun hideAll() {
        loadingLayout.hide()
        errorLayout.hide()
        contentView.invisible()
    }

    private fun onSuccess() {
        hideAll()
        contentView.show()
    }

    private fun onLoading() {
        hideAll()
        loadingLayout.show()
    }

    private fun onError() {
        hideAll()
        errorLayout.show()
    }
}