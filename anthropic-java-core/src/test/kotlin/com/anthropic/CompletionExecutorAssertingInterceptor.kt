package com.anthropic

import com.anthropic.core.http.HttpClient
import com.anthropic.core.http.Interceptor
import org.assertj.core.api.Assertions.assertThat

/**
 * Fails an asynchronous request that is sent, or whose response arrives, in a thread other than
 * those of the default `completionExecutor`.
 */
internal class CompletionExecutorAssertingInterceptor : Interceptor {

    override fun intercept(httpClient: HttpClient): HttpClient =
        Interceptor.asyncOnly { nextClient, request, requestOptions ->
                assertCompletionExecutorThread()
                nextClient.executeAsync(request, requestOptions).whenComplete { _, _ ->
                    assertCompletionExecutorThread()
                }
            }
            .intercept(httpClient)

    private fun assertCompletionExecutorThread() {
        assertThat(Thread.currentThread().name).startsWith("anthropic-completion-thread-")
    }
}
