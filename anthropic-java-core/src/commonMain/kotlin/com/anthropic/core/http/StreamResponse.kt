package com.anthropic.core.http

import java.lang.AutoCloseable
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

interface StreamResponse<T> : java.lang.AutoCloseable {

    fun stream(): Flow<T>

    /** Overridden from [java.lang.AutoCloseable] to not have a checked exception in its signature. */
    override fun close()
}

internal fun <T, R> StreamResponse<T>.map(transform: (T) -> R): StreamResponse<R> =
    object : StreamResponse<R> {
        override fun stream(): Flow<R> = this@map.stream().map(transform)

        override fun close() = this@map.close()
    }
