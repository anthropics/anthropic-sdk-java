package com.anthropic.core.http

import com.anthropic.core.RequestOptions
import java.util.concurrent.CompletableFuture
import java.util.concurrent.RejectedExecutionException
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.catchThrowable
import org.junit.jupiter.api.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

internal class CompletionExecutorHttpClientTest {

    private val request =
        HttpRequest.builder().method(HttpMethod.GET).baseUrl("https://api.example.com").build()
    private val response = mock<HttpResponse>()
    private val responseFuture = CompletableFuture<HttpResponse>()
    private val httpClient =
        CompletionExecutorHttpClient(
            httpClient = mock { on { executeAsync(any(), any()) } doReturn responseFuture },
            completionExecutor = { throw RejectedExecutionException() },
        )

    @Test
    fun executeAsync_whenExecutorRejectsCompletedResponse_closesResponse() {
        responseFuture.complete(response)

        val future = httpClient.executeAsync(request, RequestOptions.none())

        assertThat(catchThrowable { future.join() })
            .hasCauseInstanceOf(RejectedExecutionException::class.java)
        verify(response).close()
    }

    @Test
    fun executeAsync_whenExecutorRejectsLaterResponse_closesResponse() {
        val future = httpClient.executeAsync(request, RequestOptions.none())

        responseFuture.complete(response)

        assertThat(catchThrowable { future.join() })
            .hasCauseInstanceOf(RejectedExecutionException::class.java)
        verify(response).close()
    }
}
