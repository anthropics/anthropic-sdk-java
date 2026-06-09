package com.anthropic.helpers

import com.anthropic.core.RequestOptions
import java.util.concurrent.atomic.AtomicInteger

/**
 * Tracks which fallback a sequence of requests is pinned to.
 *
 * Create one ([create]) and pass it via the [RequestOptions.Builder.fallbackState] request option
 * on every request that should share the pin — the turns of one conversation, or any wider scope
 * the stickiness should apply to; [BetaRefusalFallbackInterceptor] mutates it in place when a model
 * refuses.
 */
class BetaFallbackState private constructor() {

    companion object {

        /** Returns a new [BetaFallbackState] pinned to the original request params. */
        @JvmStatic fun create(): BetaFallbackState = BetaFallbackState()
    }

    private val index = AtomicInteger(-1)

    /**
     * Returns the index into the fallback chain the requests are pinned to.
     *
     * -1 targets the original request params; the interceptor sets it to the index of the fallback
     * that accepted the request.
     */
    fun index(): Int = index.get()

    /** Sets the index into the fallback chain the requests are pinned to ([index]). */
    fun index(index: Int) = this.index.set(index)
}
