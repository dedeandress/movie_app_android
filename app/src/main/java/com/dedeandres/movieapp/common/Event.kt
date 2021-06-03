package com.dedeandres.scaffoldproject.common

import java.util.concurrent.atomic.AtomicBoolean

class Event<out T>(
    private val content: T
) {

    private val hasBeenHandled = AtomicBoolean(false)

    /*
    * Return content if it's not already in use
    */
    fun getContentIfNotHandled(handleContent: (T) -> Unit) {
        if (hasBeenHandled.compareAndSet(false, true)) {
            handleContent(content)
        }
    }

    /*
    * Return content even if it's already been handled
    */
    fun peekContent() = content
}