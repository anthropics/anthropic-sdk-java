
package kotlinx.kmp.util.core.handlers

import kotlinx.kmp.util.core.http.HttpResponse
import kotlinx.kmp.util.core.http.HttpResponse.Handler
import kotlinx.kmp.util.core.http.PhantomReachableClosingStreamResponse
import kotlinx.kmp.util.core.http.StreamResponse
import kotlinx.kmp.util.core.errors.ApiIoException

fun <T> streamHandler(
    block: suspend SequenceScope<T>.(response: HttpResponse, lines: Sequence<String>) -> Unit
): Handler<StreamResponse<T>> =
    object : Handler<StreamResponse<T>> {

        override fun handle(response: HttpResponse): StreamResponse<T> {
            val source = response.body()
            val lines = sequence {
                try {
                    while (true) {
                        val line = source.readUtf8Line() ?: break
                        yield(line)
                    }
                } catch (e: Exception) {
                    if (e is kotlinx.kmp.util.core.http.Retryable) {
                        throw ApiIoException("Stream failed", e)
                    }
                    throw e
                }
            }

            val sequence =
                CloseableSequence(
                    sequence { block(response, lines) }.constrainOnce()
                )

            return PhantomReachableClosingStreamResponse(
                object : StreamResponse<T> {

                    override fun stream(): kotlinx.coroutines.flow.Flow<T> = kotlinx.coroutines.flow.flow { sequence.forEach { emit(it) } }

                    override fun close() {
                        sequence.close()
                        source.close()
                        response.close()
                    }
                }
            )
        }
    }

/**
 * A sequence that can be closed.
 *
 * Once [close] is called, it will not yield more elements. It will also no longer consult the
 * underlying [Iterator.hasNext] method.
 */
private class CloseableSequence<T>(private val sequence: Sequence<T>) : Sequence<T> {

    private var isClosed: Boolean = false

    override fun iterator(): Iterator<T> {
        val iterator = sequence.iterator()
        return object : Iterator<T> {

            override fun next(): T = iterator.next()

            override fun hasNext(): Boolean = !isClosed && iterator.hasNext()
        }
    }

    fun close() {
        isClosed = true
    }
}
