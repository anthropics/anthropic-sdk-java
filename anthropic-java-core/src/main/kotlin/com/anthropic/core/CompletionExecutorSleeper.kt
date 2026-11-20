package com.anthropic.core

import java.time.Duration
import java.util.concurrent.CompletableFuture
import java.util.concurrent.Executor

/**
 * A delegating wrapper around a [Sleeper] that completes its futures in [completionExecutor].
 *
 * This class ensures that nothing chained onto those futures runs in the [Sleeper]'s own threads,
 * unless [completionExecutor] runs a task in the thread that submits it.
 */
internal class CompletionExecutorSleeper(
    private val sleeper: Sleeper,
    private val completionExecutor: Executor,
) : Sleeper {

    override fun sleep(duration: Duration) = sleeper.sleep(duration)

    override fun sleepAsync(duration: Duration): CompletableFuture<Void> =
        sleeper.sleepAsync(duration).whenCompleteAsync({ _, _ -> }, completionExecutor)

    override fun close() = sleeper.close()
}
