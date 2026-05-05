// File generated from our OpenAPI spec by Stainless.

package com.anthropic.services.async.beta.sessions

import com.anthropic.TestServerExtension
import com.anthropic.client.okhttp.AnthropicOkHttpClientAsync
import com.anthropic.models.beta.AnthropicBeta
import com.anthropic.models.beta.sessions.threads.ThreadArchiveParams
import com.anthropic.models.beta.sessions.threads.ThreadRetrieveParams
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(TestServerExtension::class)
internal class ThreadServiceAsyncTest {

    @Test
    fun retrieve() {
        val client =
            AnthropicOkHttpClientAsync.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val threadServiceAsync = client.beta().sessions().threads()

        val betaManagedAgentsSessionThreadFuture =
            threadServiceAsync.retrieve(
                ThreadRetrieveParams.builder()
                    .sessionId("sesn_011CZkZAtmR3yMPDzynEDxu7")
                    .threadId("sthr_011CZkZVWa6oIjw0rgXZpnBt")
                    .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                    .build()
            )

        val betaManagedAgentsSessionThread = betaManagedAgentsSessionThreadFuture.get()
        betaManagedAgentsSessionThread.validate()
    }

    @Disabled("buildURL drops path-level query params (SDK-4349)")
    @Test
    fun list() {
        val client =
            AnthropicOkHttpClientAsync.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val threadServiceAsync = client.beta().sessions().threads()

        val pageFuture = threadServiceAsync.list("sesn_011CZkZAtmR3yMPDzynEDxu7")

        val page = pageFuture.get()
        page.response().validate()
    }

    @Test
    fun archive() {
        val client =
            AnthropicOkHttpClientAsync.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val threadServiceAsync = client.beta().sessions().threads()

        val betaManagedAgentsSessionThreadFuture =
            threadServiceAsync.archive(
                ThreadArchiveParams.builder()
                    .sessionId("sesn_011CZkZAtmR3yMPDzynEDxu7")
                    .threadId("sthr_011CZkZVWa6oIjw0rgXZpnBt")
                    .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                    .build()
            )

        val betaManagedAgentsSessionThread = betaManagedAgentsSessionThreadFuture.get()
        betaManagedAgentsSessionThread.validate()
    }
}
