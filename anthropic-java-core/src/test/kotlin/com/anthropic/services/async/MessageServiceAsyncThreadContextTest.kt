package com.anthropic.services.async

import com.anthropic.client.AnthropicClientAsyncImpl
import com.anthropic.core.ClientOptions
import com.anthropic.core.RequestOptions
import com.anthropic.core.http.HttpClient
import com.anthropic.core.http.HttpRequest
import com.anthropic.core.http.HttpResponse
import com.anthropic.models.beta.messages.MessageCreateParams as BetaMessageCreateParams
import com.anthropic.models.beta.messages.BetaMessageParam
import com.anthropic.models.beta.messages.MessageCountTokensParams as BetaMessageCountTokensParams
import com.anthropic.models.messages.MessageCountTokensParams
import com.anthropic.models.messages.MessageCreateParams
import com.anthropic.models.messages.Model
import java.util.concurrent.CompletableFuture
import java.util.concurrent.TimeUnit
import java.util.concurrent.atomic.AtomicReference
import kotlin.test.Test
import kotlin.test.assertEquals

internal class MessageServiceAsyncThreadContextTest {
    private val callerContext = ThreadLocal<String>()

    @Test
    fun create_executesHttpClientWithCallerThreadContext() {
        assertCallerContext { client ->
            client.messages().withRawResponse().create(messageParams())
        }
    }

    @Test
    fun createStreaming_executesHttpClientWithCallerThreadContext() {
        assertCallerContext { client ->
            client.messages().withRawResponse().createStreaming(messageParams())
        }
    }

    @Test
    fun countTokens_executesHttpClientWithCallerThreadContext() {
        assertCallerContext { client ->
            client.messages().withRawResponse().countTokens(countTokensParams())
        }
    }

    @Test
    fun betaCreate_executesHttpClientWithCallerThreadContext() {
        assertCallerContext { client ->
            client.beta().messages().withRawResponse().create(betaMessageParams())
        }
    }

    @Test
    fun betaCreateStreaming_executesHttpClientWithCallerThreadContext() {
        assertCallerContext { client ->
            client.beta().messages().withRawResponse().createStreaming(betaMessageParams())
        }
    }

    private fun assertCallerContext(
        send: (AnthropicClientAsyncImpl) -> CompletableFuture<*>,
    ) {
        val observedContext = AtomicReference<String?>()
        val httpClient =
            object : HttpClient {
                override fun execute(
                    request: HttpRequest,
                    requestOptions: RequestOptions,
                ): HttpResponse = error("The synchronous HTTP path must not be used")

                override fun executeAsync(
                    request: HttpRequest,
                    requestOptions: RequestOptions,
                ): CompletableFuture<HttpResponse> {
                    observedContext.set(callerContext.get())
                    return CompletableFuture<HttpResponse>().also {
                        it.completeExceptionally(IllegalStateException("synthetic response"))
                    }
                }

                override fun close() = Unit
            }
        val client =
            AnthropicClientAsyncImpl(
                ClientOptions.builder()
                    .baseUrl("http://127.0.0.1")
                    .maxRetries(0)
                    .httpClient(httpClient)
                    .build()
            )

        try {
            callerContext.set("caller-context")
            send(client).handle { _, _ -> null }.get(5, TimeUnit.SECONDS)
            assertEquals("caller-context", observedContext.get())
        } finally {
            callerContext.remove()
            client.close()
        }
    }

    private fun messageParams() =
        MessageCreateParams.builder()
            .model(Model.CLAUDE_OPUS_5_5)
            .maxTokens(1L)
            .addUserMessage("synthetic")
            .build()

    @Test
    fun betaCountTokens_executesHttpClientWithCallerThreadContext() {
        assertCallerContext { client ->
            client.beta().messages().withRawResponse().countTokens(betaCountTokensParams())
        }
    }

    private fun countTokensParams() =
        MessageCountTokensParams.builder()
            .model(Model.CLAUDE_OPUS_5_5)
            .addUserMessage("synthetic")
            .build()

    private fun betaCountTokensParams() =
        BetaMessageCountTokensParams.builder()
            .model(Model.CLAUDE_OPUS_5_5)
            .addUserMessage("synthetic")
            .build()

    private fun betaMessageParams() =
        BetaMessageCreateParams.builder()
            .model(Model.CLAUDE_OPUS_5_5)
            .maxTokens(1L)
            .addMessage(
                BetaMessageParam.builder()
                    .content("synthetic")
                    .role(BetaMessageParam.Role.USER)
                    .build()
            )
            .build()
}
