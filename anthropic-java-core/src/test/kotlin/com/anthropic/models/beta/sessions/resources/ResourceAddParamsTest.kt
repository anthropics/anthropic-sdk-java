// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.sessions.resources

import com.anthropic.core.http.Headers
import com.anthropic.models.beta.AnthropicBeta
import com.anthropic.models.beta.sessions.BetaManagedAgentsFileResourceParams
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ResourceAddParamsTest {

    @Test
    fun create() {
        ResourceAddParams.builder()
            .sessionId("sesn_011CZkZAtmR3yMPDzynEDxu7")
            .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
            .betaManagedAgentsFileResourceParams(
                BetaManagedAgentsFileResourceParams.builder()
                    .fileId("file_011CNha8iCJcU1wXNR6q4V8w")
                    .type(BetaManagedAgentsFileResourceParams.Type.FILE)
                    .mountPath("/uploads/receipt.pdf")
                    .build()
            )
            .build()
    }

    @Test
    fun pathParams() {
        val params =
            ResourceAddParams.builder()
                .sessionId("sesn_011CZkZAtmR3yMPDzynEDxu7")
                .betaManagedAgentsFileResourceParams(
                    BetaManagedAgentsFileResourceParams.builder()
                        .fileId("file_011CNha8iCJcU1wXNR6q4V8w")
                        .type(BetaManagedAgentsFileResourceParams.Type.FILE)
                        .build()
                )
                .build()

        assertThat(params._pathParam(0)).isEqualTo("sesn_011CZkZAtmR3yMPDzynEDxu7")
        // out-of-bound path param
        assertThat(params._pathParam(1)).isEqualTo("")
    }

    @Test
    fun headers() {
        val params =
            ResourceAddParams.builder()
                .sessionId("sesn_011CZkZAtmR3yMPDzynEDxu7")
                .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                .betaManagedAgentsFileResourceParams(
                    BetaManagedAgentsFileResourceParams.builder()
                        .fileId("file_011CNha8iCJcU1wXNR6q4V8w")
                        .type(BetaManagedAgentsFileResourceParams.Type.FILE)
                        .mountPath("/uploads/receipt.pdf")
                        .build()
                )
                .build()

        val headers = params._headers()

        assertThat(headers)
            .isEqualTo(
                Headers.builder().put("anthropic-beta", "message-batches-2024-09-24").build()
            )
    }

    @Test
    fun headersWithoutOptionalFields() {
        val params =
            ResourceAddParams.builder()
                .sessionId("sesn_011CZkZAtmR3yMPDzynEDxu7")
                .betaManagedAgentsFileResourceParams(
                    BetaManagedAgentsFileResourceParams.builder()
                        .fileId("file_011CNha8iCJcU1wXNR6q4V8w")
                        .type(BetaManagedAgentsFileResourceParams.Type.FILE)
                        .build()
                )
                .build()

        val headers = params._headers()

        assertThat(headers).isEqualTo(Headers.builder().build())
    }

    @Test
    fun body() {
        val params =
            ResourceAddParams.builder()
                .sessionId("sesn_011CZkZAtmR3yMPDzynEDxu7")
                .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                .betaManagedAgentsFileResourceParams(
                    BetaManagedAgentsFileResourceParams.builder()
                        .fileId("file_011CNha8iCJcU1wXNR6q4V8w")
                        .type(BetaManagedAgentsFileResourceParams.Type.FILE)
                        .mountPath("/uploads/receipt.pdf")
                        .build()
                )
                .build()

        val body = params._body()

        assertThat(body)
            .isEqualTo(
                BetaManagedAgentsFileResourceParams.builder()
                    .fileId("file_011CNha8iCJcU1wXNR6q4V8w")
                    .type(BetaManagedAgentsFileResourceParams.Type.FILE)
                    .mountPath("/uploads/receipt.pdf")
                    .build()
            )
    }

    @Test
    fun bodyWithoutOptionalFields() {
        val params =
            ResourceAddParams.builder()
                .sessionId("sesn_011CZkZAtmR3yMPDzynEDxu7")
                .betaManagedAgentsFileResourceParams(
                    BetaManagedAgentsFileResourceParams.builder()
                        .fileId("file_011CNha8iCJcU1wXNR6q4V8w")
                        .type(BetaManagedAgentsFileResourceParams.Type.FILE)
                        .build()
                )
                .build()

        val body = params._body()

        assertThat(body)
            .isEqualTo(
                BetaManagedAgentsFileResourceParams.builder()
                    .fileId("file_011CNha8iCJcU1wXNR6q4V8w")
                    .type(BetaManagedAgentsFileResourceParams.Type.FILE)
                    .build()
            )
    }
}
