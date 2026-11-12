package com.anthropic.models.beta.environments

import com.anthropic.core.http.Headers
import com.anthropic.models.beta.AnthropicBeta
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class EnvironmentArchiveParamsTest {

    @Test
    fun create() {
        EnvironmentArchiveParams.builder()
            .environmentId("env_011CZkZ9X2dpNyB7HsEFoRfW")
            .addBeta(AnthropicBeta.COMPUTER_USE_2024_10_22)
            .workspaceId("wrkspc_011CZkZaBF1tNoB5wlCeusgy")
            .build()
    }

    @Test
    fun pathParams() {
        val params =
            EnvironmentArchiveParams.builder().environmentId("env_011CZkZ9X2dpNyB7HsEFoRfW").build()

        assertThat(params._pathParam(0)).isEqualTo("env_011CZkZ9X2dpNyB7HsEFoRfW")
        // out-of-bound path param
        assertThat(params._pathParam(1)).isEqualTo("")
    }

    @Test
    fun headers() {
        val params =
            EnvironmentArchiveParams.builder()
                .environmentId("env_011CZkZ9X2dpNyB7HsEFoRfW")
                .addBeta(AnthropicBeta.COMPUTER_USE_2024_10_22)
                .workspaceId("wrkspc_011CZkZaBF1tNoB5wlCeusgy")
                .build()

        val headers = params._headers()

        assertThat(headers)
            .isEqualTo(
                Headers.builder()
                    .put(
                        "anthropic-beta",
                        listOf("computer-use-2024-10-22", "managed-agents-2026-04-01")
                            .joinToString(","),
                    )
                    .put("anthropic-workspace-id", "wrkspc_011CZkZaBF1tNoB5wlCeusgy")
                    .build()
            )
    }

    @Test
    fun headersWithoutOptionalFields() {
        val params =
            EnvironmentArchiveParams.builder().environmentId("env_011CZkZ9X2dpNyB7HsEFoRfW").build()

        val headers = params._headers()

        assertThat(headers).isEqualTo(Headers.builder().build())
    }
}
