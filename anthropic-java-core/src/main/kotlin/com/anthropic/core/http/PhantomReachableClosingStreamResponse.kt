package com.anthropic.core.http

import com.anthropic.core.closeWhenPhantomReachable
import java.util.stream.Stream
import kotlin.streams.asStream

/**
 * A delegating wrapper around a `StreamResponse` that closes it once it's only phantom reachable.
 *
 * This class ensures the `StreamResponse` is closed even if the user forgets to close it.
 */
internal class PhantomReachableClosingStreamResponse<T>(
    private val streamResponse: StreamResponse<T>
) : StreamResponse<T> {
    init {
        closeWhenPhantomReachable(this, streamResponse)
    }

    /**
     * Returns a stream that closes this response once consumed, so it stays reachable until then.
     *
     * Otherwise a caller that only holds the stream, like
     * `createStreaming(...).stream().forEach(...)`, would let this response become phantom
     * reachable mid-iteration, which closes it and silently ends the stream.
     */
    override fun stream(): Stream<T> =
        sequence {
                yieldAll(streamResponse.stream().iterator())
                close()
            }
            .asStream()
            .onClose(::close)

    override fun close() = streamResponse.close()
}
