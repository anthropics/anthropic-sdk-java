@file:JvmName("ClientOptionsExtJvm")

package kotlinx.kmp.util.core

import java.util.concurrent.Executor

/** JVM-typed accessor for the stream handler executor. */
fun ClientOptions.streamExecutor(): Executor = streamHandlerExecutor as Executor
