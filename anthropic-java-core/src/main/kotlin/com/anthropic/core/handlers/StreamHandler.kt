@file:JvmName("StreamHandler")

package com.anthropic.core.handlers

import com.anthropic.core.http.HttpResponse
import com.anthropic.core.http.HttpResponse.Handler
import com.anthropic.core.http.PhantomReachableClosingStreamResponse
import com.anthropic.core.http.StreamResponse
import java.util.stream.Stream
import kotlin.streams.asStream

@JvmSynthetic
internal fun <T> streamHandler(
    block: suspend SequenceScope<T>.(lines: Sequence<String>) -> Unit
): Handler<StreamResponse<T>> =
    object : Handler<StreamResponse<T>> {
        override fun handle(response: HttpResponse): StreamResponse<T> {
            val reader = response.body().bufferedReader()
            val sequence =
                // Wrap in an `InterruptibleSequence` to avoid performing a read on the `reader`
                // after it has
                // been closed, which would throw an `IOException`.
                InterruptibleSequence(sequence { reader.useLines { block(it) } }.constrainOnce())

            return PhantomReachableClosingStreamResponse(
                object : StreamResponse<T> {
                    override fun stream(): Stream<T> = sequence.asStream()

                    override fun close() {
                        sequence.interrupt()
                        reader.close()
                        response.close()
                    }
                }
            )
        }
    }

/**
 * A sequence that can be interrupted.
 *
 * Once [interrupt] is called, it will not yield more elements. It will also no longer consult the
 * underlying [Iterator.hasNext] method.
 */
private class InterruptibleSequence<T>(private val sequence: Sequence<T>) : Sequence<T> {
    private var interrupted: Boolean = false

    override fun iterator(): Iterator<T> {
        val iterator = sequence.iterator()
        return object : Iterator<T> {
            override fun next(): T = iterator.next()

            override fun hasNext(): Boolean = !interrupted && iterator.hasNext()
        }
    }

    fun interrupt() {
        interrupted = true
    }
}
