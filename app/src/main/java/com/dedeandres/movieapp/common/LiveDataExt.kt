package com.dedeandres.movieapp.common

import androidx.lifecycle.MutableLiveData
import com.dedeandres.scaffoldproject.common.Event

fun <T> MutableLiveData<Event<Resource<T>>>.setSuccessEvent(data: T): MutableLiveData<Event<Resource<T>>> {
    postValue(
        Event(
            Resource(
                ResourceState.SUCCESS,
                data
            )
        )
    )
    return this
}

fun <T> MutableLiveData<Event<Resource<T>>>.setLoadingEvent() =
    postValue(
        Event(
            Resource(ResourceState.LOADING)
        )
    )

fun <T> MutableLiveData<Event<Resource<T>>>.setSuccessEvent() =
    postValue(
        Event(
            Resource(
                ResourceState.SUCCESS,
                null
            )
        )
    )

fun <T> MutableLiveData<Event<Resource<T>>>.setErrorEvent(exception: Exception? = null) =
    postValue(
        Event(
            Resource(
                ResourceState.ERROR,
                value?.peekContent()?.data,
                exception
            )
        )
    )
