// File generated from our OpenAPI spec by Stainless.

package com.anthropic.core

import com.anthropic.core.http.AsyncStreamResponse

import java.util.concurrent.CompletableFuture
import java.util.concurrent.Executor
import java.util.concurrent.atomic.AtomicReference
import kotlinx.coroutines.runBlocking

class AutoPagerAsync<T>
private constructor(private val firstPage: PageAsync<T>, private val defaultExecutor: Executor) :
    AsyncStreamResponse<T> {

    companion object {

        @JvmStatic fun <T> from(firstPage: PageAsync<T>, defaultExecutor: Executor): AutoPagerAsync<T> =
            AutoPagerAsync(firstPage, defaultExecutor)
    }

    private val onCompleteFuture = CompletableFuture<Void?>()
    private val state = AtomicReference(State.NEW)

    override fun subscribe(handler: AsyncStreamResponse.Handler<T>): AsyncStreamResponse<T> =
        subscribe(handler, defaultExecutor)

    override fun subscribe(
        handler: AsyncStreamResponse.Handler<T>,
        executor: Executor,
    ): AsyncStreamResponse<T> = apply {
        check(state.compareAndSet(State.NEW, State.SUBSCRIBED)) {
            if (state.get() == State.SUBSCRIBED) "Cannot subscribe more than once"
            else "Cannot subscribe after the response is closed"
        }

        executor.execute {
            var error: Throwable? = null
            try {
                runBlocking {
                    var page: PageAsync<T> = firstPage
                    while (true) {
                        if (state.get() == State.CLOSED) break
                        page.items().forEach { handler.onNext(it) }
                        if (!page.hasNextPage()) break
                        page = page.nextPage()
                    }
                }
            } catch (e: Throwable) {
                error = e
            }

            try {
                handler.onComplete(error)
            } finally {
                try {
                    if (error == null) {
                        onCompleteFuture.complete(null)
                    } else {
                        onCompleteFuture.completeExceptionally(error)
                    }
                } finally {
                    close()
                }
            }
        }
    }

    override fun onCompleteFuture(): CompletableFuture<Void?> = onCompleteFuture

    override fun close() {
        val previousState = state.getAndSet(State.CLOSED)
        if (previousState == State.CLOSED) {
            return
        }

        onCompleteFuture.complete(null)
    }
}

private enum class State {
    NEW,
    SUBSCRIBED,
    CLOSED,
}
