package kotlinx.kmp.util.core.http

import kotlinx.kmp.util.core.closeWhenPhantomReachable

/**
 * A delegating wrapper around a `StreamResponse` that closes it once it's only phantom reachable.
 *
 * This class ensures the `StreamResponse` is closed even if the user forgets to close it.
 */
class PhantomReachableClosingStreamResponse<T>(
    private val streamResponse: StreamResponse<T>
) : StreamResponse<T> {
    init {
        closeWhenPhantomReachable(this, streamResponse)
    }

    override fun stream(): kotlinx.coroutines.flow.Flow<T> = streamResponse.stream()

    override fun close() = streamResponse.close()
}
