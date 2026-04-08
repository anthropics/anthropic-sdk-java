// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.sessions

import com.anthropic.core.JsonValue
import com.anthropic.core.http.Headers
import com.anthropic.models.beta.AnthropicBeta
import kotlin.jvm.optionals.getOrNull
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class SessionUpdateParamsTest {

    @Test
    fun create() {
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
    }

    @Test
    fun pathParams() {
        val params =
            SessionUpdateParams.builder().sessionId("sesn_011CZkZAtmR3yMPDzynEDxu7").build()

        assertThat(params._pathParam(0)).isEqualTo("sesn_011CZkZAtmR3yMPDzynEDxu7")
        // out-of-bound path param
        assertThat(params._pathParam(1)).isEqualTo("")
    }

    @Test
    fun headers() {
        val params =
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

        val headers = params._headers()

        assertThat(headers)
            .isEqualTo(
                Headers.builder().put("anthropic-beta", "message-batches-2024-09-24").build()
            )
    }

    @Test
    fun headersWithoutOptionalFields() {
        val params =
            SessionUpdateParams.builder().sessionId("sesn_011CZkZAtmR3yMPDzynEDxu7").build()

        val headers = params._headers()

        assertThat(headers).isEqualTo(Headers.builder().build())
    }

    @Test
    fun body() {
        val params =
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

        val body = params._body()

        assertThat(body.metadata())
            .contains(
                SessionUpdateParams.Metadata.builder()
                    .putAdditionalProperty("foo", JsonValue.from("string"))
                    .build()
            )
        assertThat(body.title()).contains("Order #1234 inquiry")
        assertThat(body.vaultIds().getOrNull()).containsExactly("string")
    }

    @Test
    fun bodyWithoutOptionalFields() {
        val params =
            SessionUpdateParams.builder().sessionId("sesn_011CZkZAtmR3yMPDzynEDxu7").build()

        val body = params._body()
    }
}
