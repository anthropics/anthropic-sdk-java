package com.anthropic.core.http

import com.anthropic.core.PlatformCloseable

interface StreamResponse<T> : PlatformCloseable {

    fun stream(): Sequence<T>

    /** Overridden from [PlatformCloseable] to not have a checked exception in its signature. */
    override fun close()
}

internal fun <T, R> StreamResponse<T>.map(transform: (T) -> R): StreamResponse<R> =
    object : StreamResponse<R> {
        override fun stream(): Sequence<R> = this@map.stream().map(transform)

        override fun close() = this@map.close()
    }
