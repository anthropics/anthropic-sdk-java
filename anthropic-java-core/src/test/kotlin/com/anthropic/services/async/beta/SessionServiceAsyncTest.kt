// File generated from our OpenAPI spec by Stainless.

package com.anthropic.services.async.beta

import com.anthropic.TestServerExtension
import com.anthropic.client.okhttp.AnthropicOkHttpClientAsync
import com.anthropic.core.JsonValue
import com.anthropic.models.beta.AnthropicBeta
import com.anthropic.models.beta.sessions.BetaManagedAgentsFileResourceParams
import com.anthropic.models.beta.sessions.SessionArchiveParams
import com.anthropic.models.beta.sessions.SessionCreateParams
import com.anthropic.models.beta.sessions.SessionDeleteParams
import com.anthropic.models.beta.sessions.SessionRetrieveParams
import com.anthropic.models.beta.sessions.SessionUpdateParams
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(TestServerExtension::class)
internal class SessionServiceAsyncTest {

    @Test
    fun create() {
        val client =
            AnthropicOkHttpClientAsync.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val sessionServiceAsync = client.beta().sessions()

        val betaManagedAgentsSessionFuture =
            sessionServiceAsync.create(
                SessionCreateParams.builder()
                    .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                    .agent("agent_011CZkYpogX7uDKUyvBTophP")
                    .environmentId("env_011CZkZ9X2dpNyB7HsEFoRfW")
                    .metadata(
                        SessionCreateParams.Metadata.builder()
                            .putAdditionalProperty("foo", JsonValue.from("string"))
                            .build()
                    )
                    .addResource(
                        BetaManagedAgentsFileResourceParams.builder()
                            .fileId("file_011CNha8iCJcU1wXNR6q4V8w")
                            .type(BetaManagedAgentsFileResourceParams.Type.FILE)
                            .mountPath("/uploads/receipt.pdf")
                            .build()
                    )
                    .title("Order #1234 inquiry")
                    .addVaultId("string")
                    .build()
            )

        val betaManagedAgentsSession = betaManagedAgentsSessionFuture.get()
        betaManagedAgentsSession.validate()
    }

    @Test
    fun retrieve() {
        val client =
            AnthropicOkHttpClientAsync.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val sessionServiceAsync = client.beta().sessions()

        val betaManagedAgentsSessionFuture =
            sessionServiceAsync.retrieve(
                SessionRetrieveParams.builder()
                    .sessionId("sesn_011CZkZAtmR3yMPDzynEDxu7")
                    .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                    .build()
            )

        val betaManagedAgentsSession = betaManagedAgentsSessionFuture.get()
        betaManagedAgentsSession.validate()
    }

    @Test
    fun update() {
        val client =
            AnthropicOkHttpClientAsync.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val sessionServiceAsync = client.beta().sessions()

        val betaManagedAgentsSessionFuture =
            sessionServiceAsync.update(
                SessionUpdateParams.builder()
                    .sessionId("sesn_011CZkZAtmR3yMPDzynEDxu7")
                    .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                    .metadata(
                        SessionUpdateParams.Metadata.builder()
                            .putAdditionalProperty("foo", JsonValue.from("string"))
                            .build()
                    )
                    .title("Order #1234 inquiry")
                    .addVaultId("string")
                    .build()
            )

        val betaManagedAgentsSession = betaManagedAgentsSessionFuture.get()
        betaManagedAgentsSession.validate()
    }

    @Disabled("buildURL drops path-level query params (SDK-4349)")
    @Test
    fun list() {
        val client =
            AnthropicOkHttpClientAsync.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val sessionServiceAsync = client.beta().sessions()

        val pageFuture = sessionServiceAsync.list()

        val page = pageFuture.get()
        page.response().validate()
    }

    @Test
    fun delete() {
        val client =
            AnthropicOkHttpClientAsync.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val sessionServiceAsync = client.beta().sessions()

        val betaManagedAgentsDeletedSessionFuture =
            sessionServiceAsync.delete(
                SessionDeleteParams.builder()
                    .sessionId("sesn_011CZkZAtmR3yMPDzynEDxu7")
                    .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                    .build()
            )

        val betaManagedAgentsDeletedSession = betaManagedAgentsDeletedSessionFuture.get()
        betaManagedAgentsDeletedSession.validate()
    }

    @Test
    fun archive() {
        val client =
            AnthropicOkHttpClientAsync.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val sessionServiceAsync = client.beta().sessions()

        val betaManagedAgentsSessionFuture =
            sessionServiceAsync.archive(
                SessionArchiveParams.builder()
                    .sessionId("sesn_011CZkZAtmR3yMPDzynEDxu7")
                    .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                    .build()
            )

        val betaManagedAgentsSession = betaManagedAgentsSessionFuture.get()
        betaManagedAgentsSession.validate()
    }
}
