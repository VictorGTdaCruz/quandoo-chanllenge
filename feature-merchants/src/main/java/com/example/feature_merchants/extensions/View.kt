package com.example.feature_merchants.extensions

import android.content.ContextWrapper
import android.view.View
import androidx.fragment.app.FragmentActivity

fun View.getActivity(): FragmentActivity {
    var context = context
    while (context is ContextWrapper) {
        if (context is FragmentActivity) {
            return context
        }
        context = context.baseContext
    }
    throw Exception()
}

fun View.hide() {
    visibility = View.GONE
}

fun View.show() {
    visibility = View.VISIBLE
}
