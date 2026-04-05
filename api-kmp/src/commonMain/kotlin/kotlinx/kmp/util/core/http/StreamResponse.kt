package kotlinx.kmp.util.core.http

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

interface StreamResponse<T> : AutoCloseable {

    fun stream(): Flow<T>

    /** Overridden from [AutoCloseable] to not have a checked exception in its signature. */
    override fun close()
}

fun <T, R> StreamResponse<T>.map(transform: (T) -> R): StreamResponse<R> =
    object : StreamResponse<R> {
        override fun stream(): Flow<R> = this@map.stream().map(transform)

        override fun close() = this@map.close()
    }
