package com.anthropic.core.http

import com.anthropic.core.PlatformCloseable
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

interface StreamResponse<T> : PlatformCloseable {

    fun stream(): Flow<T>

    /** Overridden from [PlatformCloseable] to not have a checked exception in its signature. */
    override fun close()
}

internal fun <T, R> StreamResponse<T>.map(transform: (T) -> R): StreamResponse<R> =
    object : StreamResponse<R> {
        override fun stream(): Flow<R> = this@map.stream().map(transform)

        override fun close() = this@map.close()
    }
