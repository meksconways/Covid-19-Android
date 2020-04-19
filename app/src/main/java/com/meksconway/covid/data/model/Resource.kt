package com.meksconway.covid.data.model

data class Resource<out T>(
    val status: Status,
    val data: T?,
    val message: String?
) {

    enum class Status {
        LOADING,
        ERROR,
        SUCCESS
    }

    companion object {

        @JvmStatic
        fun <T> success(data: T): Resource<T> {
            return Resource(Status.SUCCESS, data, null)
        }

        @JvmStatic
        fun <T> error(message: String?): Resource<T> {
            return Resource(Status.ERROR, null, message)
        }

        @JvmStatic
        fun <T> loading(): Resource<T> {
            return Resource(Status.LOADING, null, null)
        }

    }

}