@file:JvmName("StreamHandler")

package com.anthropic.core.handlers

import com.anthropic.core.http.HttpResponse
import com.anthropic.core.http.HttpResponse.Handler
import com.anthropic.core.http.PhantomReachableClosingStreamResponse
import com.anthropic.core.http.StreamResponse
import com.anthropic.errors.AnthropicIoException
import java.io.IOException
import java.util.stream.Stream
import kotlin.streams.asStream

@JvmSynthetic
internal fun <T> streamHandler(
    block: suspend SequenceScope<T>.(response: HttpResponse, lines: Sequence<String>) -> Unit
): Handler<StreamResponse<T>> =
    object : Handler<StreamResponse<T>> {

        override fun handle(response: HttpResponse): StreamResponse<T> {
            val reader = response.body().bufferedReader()
            val sequence =
                // Wrap in a `CloseableSequence` to avoid performing a read on the `reader`
                // after it has been closed, which would throw an `IOException`.
                CloseableSequence(
                    sequence {
                            reader.useLines { lines ->
                                block(
                                    response,
                                    // We wrap the `lines` instead of the top-level sequence because
                                    // we only want to catch `IOException` from the reader; not from
                                    // the user's own code.
                                    IOExceptionWrappingSequence(lines),
                                )
                            }
                        }
                        .constrainOnce()
                )

            return PhantomReachableClosingStreamResponse(
                object : StreamResponse<T> {

                    override fun stream(): Stream<T> = sequence.asStream()

                    override fun close() {
                        sequence.close()
                        // Close the response first: it unblocks a read in flight on another
                        // thread, which holds `reader`'s lock.
                        response.close()
                        reader.close()
                    }
                }
            )
        }
    }

/** A sequence that catches, wraps, and rethrows [IOException] as [AnthropicIoException]. */
private class IOExceptionWrappingSequence<T>(private val sequence: Sequence<T>) : Sequence<T> {

    override fun iterator(): Iterator<T> {
        val iterator = sequence.iterator()
        return object : Iterator<T> {

            override fun next(): T {
                if (!hasNext()) {
                    throw NoSuchElementException()
                }

                return try {
                    iterator.next()
                } catch (e: IOException) {
                    throw AnthropicIoException("Stream failed", e)
                }
            }

            override fun hasNext(): Boolean =
                try {
                    iterator.hasNext()
                } catch (e: IOException) {
                    throw AnthropicIoException("Stream failed", e)
                }
        }
    }
}

/**
 * A sequence that can be closed, including from another thread.
 *
 * Once [close] is called, it will not yield more elements. It will also no longer consult the
 * underlying [Iterator.hasNext] method, and a read failure of an in-flight [Iterator.hasNext] ends
 * the sequence instead of propagating, because closing the stream is what failed the read.
 */
private class CloseableSequence<T>(private val sequence: Sequence<T>) : Sequence<T> {

    @Volatile private var isClosed: Boolean = false

    override fun iterator(): Iterator<T> {
        val iterator = sequence.iterator()
        return object : Iterator<T> {

            override fun next(): T {
                // Not `hasNext()`: a `close()` after `hasNext()` must still yield this element.
                if (!iterator.hasNext()) {
                    throw NoSuchElementException()
                }

                return iterator.next()
            }

            override fun hasNext(): Boolean =
                try {
                    !isClosed && iterator.hasNext()
                } catch (e: AnthropicIoException) {
                    if (isClosed) false else throw e
                }
        }
    }

    fun close() {
        isClosed = true
    }
}
