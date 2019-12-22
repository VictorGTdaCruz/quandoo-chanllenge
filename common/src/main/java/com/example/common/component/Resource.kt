package com.example.common.component

data class Resource<out T>(val status: Status,
                           val data: T? = null,
                           val error: Any? = null) {

    enum class Status {
        SUCCESS,
        ERROR,
        LOADING
    }
}