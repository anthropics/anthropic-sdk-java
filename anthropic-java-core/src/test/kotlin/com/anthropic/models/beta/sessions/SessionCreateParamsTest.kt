// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.sessions

import com.anthropic.core.JsonValue
import com.anthropic.core.http.Headers
import com.anthropic.models.beta.AnthropicBeta
import kotlin.jvm.optionals.getOrNull
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class SessionCreateParamsTest {

    @Test
    fun create() {
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
    }

    @Test
    fun headers() {
        val params =
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

        val headers = params._headers()

        assertThat(headers)
            .isEqualTo(
                Headers.builder().put("anthropic-beta", "message-batches-2024-09-24").build()
            )
    }

    @Test
    fun headersWithoutOptionalFields() {
        val params =
            SessionCreateParams.builder()
                .agent("agent_011CZkYpogX7uDKUyvBTophP")
                .environmentId("env_011CZkZ9X2dpNyB7HsEFoRfW")
                .build()

        val headers = params._headers()

        assertThat(headers).isEqualTo(Headers.builder().build())
    }

    @Test
    fun body() {
        val params =
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

        val body = params._body()

        assertThat(body.agent())
            .isEqualTo(SessionCreateParams.Agent.ofString("agent_011CZkYpogX7uDKUyvBTophP"))
        assertThat(body.environmentId()).isEqualTo("env_011CZkZ9X2dpNyB7HsEFoRfW")
        assertThat(body.metadata())
            .contains(
                SessionCreateParams.Metadata.builder()
                    .putAdditionalProperty("foo", JsonValue.from("string"))
                    .build()
            )
        assertThat(body.resources().getOrNull())
            .containsExactly(
                SessionCreateParams.Resource.ofFile(
                    BetaManagedAgentsFileResourceParams.builder()
                        .fileId("file_011CNha8iCJcU1wXNR6q4V8w")
                        .type(BetaManagedAgentsFileResourceParams.Type.FILE)
                        .mountPath("/uploads/receipt.pdf")
                        .build()
                )
            )
        assertThat(body.title()).contains("Order #1234 inquiry")
        assertThat(body.vaultIds().getOrNull()).containsExactly("string")
    }

    @Test
    fun bodyWithoutOptionalFields() {
        val params =
            SessionCreateParams.builder()
                .agent("agent_011CZkYpogX7uDKUyvBTophP")
                .environmentId("env_011CZkZ9X2dpNyB7HsEFoRfW")
                .build()

        val body = params._body()

        assertThat(body.agent())
            .isEqualTo(SessionCreateParams.Agent.ofString("agent_011CZkYpogX7uDKUyvBTophP"))
        assertThat(body.environmentId()).isEqualTo("env_011CZkZ9X2dpNyB7HsEFoRfW")
    }
}
