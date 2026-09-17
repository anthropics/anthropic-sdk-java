package com.anthropic.core.http

import java.lang.ref.WeakReference
import java.util.concurrent.TimeUnit
import java.util.stream.Stream
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assumptions.assumeTrue
import org.junit.jupiter.api.Test

internal class PhantomReachableClosingStreamResponseTest {

    @Test
    fun stream_whenOnlyStreamIsReachable_keepsWrapperReachable() {
        val (wrapper, stream) = streamWithoutWrapper(FakeStreamResponse())

        val elements = mutableListOf<Int>()
        stream.forEach {
            elements.add(it)
            assumeTrue(collectGarbage(), "Garbage collection didn't run")
            assertThat(wrapper.get()).isNotNull()
        }

        assertThat(elements).containsExactly(0, 1, 2)
    }

    @Test
    fun stream_whenConsumed_closesResponse() {
        val streamResponse = FakeStreamResponse()

        PhantomReachableClosingStreamResponse(streamResponse).stream().forEach {}

        assertThat(streamResponse.isClosed).isTrue()
    }

    @Test
    fun stream_whenClosed_closesResponse() {
        val streamResponse = FakeStreamResponse()

        PhantomReachableClosingStreamResponse(streamResponse).stream().close()

        assertThat(streamResponse.isClosed).isTrue()
    }

    /**
     * Returns a stream from a wrapper that nothing else references, and a weak reference to the
     * wrapper for observing whether it was collected.
     */
    private fun streamWithoutWrapper(streamResponse: StreamResponse<Int>) =
        PhantomReachableClosingStreamResponse(streamResponse).let {
            WeakReference(it) to it.stream()
        }

    /**
     * Runs the garbage collector until an object that became unreachable before the call is
     * collected, so any other unreachable object is collected too.
     */
    private fun collectGarbage(): Boolean {
        val sentinel = WeakReference(Any())
        val deadline = System.nanoTime() + TimeUnit.SECONDS.toNanos(10)
        while (sentinel.get() != null) {
            if (System.nanoTime() > deadline) {
                return false
            }
            System.gc()
            Thread.sleep(10)
        }
        return true
    }

    private class FakeStreamResponse : StreamResponse<Int> {

        var isClosed = false
            private set

        override fun stream(): Stream<Int> = Stream.of(0, 1, 2)

        override fun close() {
            isClosed = true
        }
    }
}
