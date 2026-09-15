package com.anthropic.core

import com.anthropic.core.http.AsyncStreamResponse
import java.util.Optional
import java.util.concurrent.CompletableFuture
import java.util.concurrent.CompletionException
import java.util.concurrent.Executor
import java.util.concurrent.atomic.AtomicReference

class AutoPagerAsync<T>
private constructor(private val firstPage: PageAsync<T>, private val defaultExecutor: Executor) :
    AsyncStreamResponse<T> {

    companion object {

        @JvmStatic
        fun <T> from(firstPage: PageAsync<T>, defaultExecutor: Executor): AutoPagerAsync<T> =
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
        // TODO(JDK): Use `compareAndExchange` once targeting JDK 9.
        check(state.compareAndSet(State.NEW, State.SUBSCRIBED)) {
            if (state.get() == State.SUBSCRIBED) "Cannot subscribe more than once"
            else "Cannot subscribe after the response is closed"
        }

        fun PageAsync<T>.handle(): CompletableFuture<Void?> {
            if (state.get() == State.CLOSED) {
                return CompletableFuture.completedFuture(null)
            }

            items().forEach { handler.onNext(it) }
            return if (hasNextPage()) nextPage().thenComposeAsync({ it.handle() }, executor)
            else CompletableFuture.completedFuture(null)
        }

        // Every handler callback runs on `executor`, never on the thread that completed a page
        // future.
        val completion =
            CompletableFuture.completedFuture(firstPage)
                .thenComposeAsync({ it.handle() }, executor)
                .whenCompleteAsync(
                    { _, error ->
                        val actualError =
                            if (error is CompletionException && error.cause != null) error.cause
                            else error
                        try {
                            handler.onComplete(Optional.ofNullable(actualError))
                        } finally {
                            try {
                                if (actualError == null) {
                                    onCompleteFuture.complete(null)
                                } else {
                                    onCompleteFuture.completeExceptionally(actualError)
                                }
                            } finally {
                                close()
                            }
                        }
                    },
                    executor,
                )

        // Not a user callback; if `executor` rejects work, the body above never runs, so finish
        // here.
        @Suppress("ForbiddenMethodCall")
        completion.whenComplete { _, error ->
            if (state.get() != State.CLOSED && error != null) {
                onCompleteFuture.completeExceptionally(error)
                close()
            }
        }
    }

    override fun onCompleteFuture(): CompletableFuture<Void?> = onCompleteFuture

    override fun close() {
        val previousState = state.getAndSet(State.CLOSED)
        if (previousState == State.CLOSED) {
            return
        }

        // When the stream is closed, we should always consider it closed. If it closed due
        // to an error, then we will have already completed the future earlier, and this
        // will be a no-op.
        onCompleteFuture.complete(null)
    }
}

private enum class State {
    NEW,
    SUBSCRIBED,
    CLOSED,
}
