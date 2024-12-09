package com.anthropic.core.http

import com.anthropic.core.closeWhenPhantomReachable
import java.util.stream.Stream

internal class PhantomReachableClosingStreamResponse<T>(
    private val streamResponse: StreamResponse<T>
) : StreamResponse<T> {
    init {
        closeWhenPhantomReachable(this, streamResponse)
    }

    override fun stream(): Stream<T> = streamResponse.stream()

    override fun close() = streamResponse.close()
}
