package com.anthropic.models.beta.organization.telemetrydestinations.workspaces

import com.anthropic.core.http.Headers
import com.anthropic.models.beta.AnthropicBeta
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class WorkspaceRemoveParamsTest {

    @Test
    fun create() {
        WorkspaceRemoveParams.builder()
            .destinationId("destination_id")
            .workspaceId("workspace_id")
            .addBeta(AnthropicBeta.COMPUTER_USE_2024_10_22)
            .build()
    }

    @Test
    fun pathParams() {
        val params =
            WorkspaceRemoveParams.builder()
                .destinationId("destination_id")
                .workspaceId("workspace_id")
                .build()

        assertThat(params._pathParam(0)).isEqualTo("destination_id")
        assertThat(params._pathParam(1)).isEqualTo("workspace_id")
        // out-of-bound path param
        assertThat(params._pathParam(2)).isEqualTo("")
    }

    @Test
    fun headers() {
        val params =
            WorkspaceRemoveParams.builder()
                .destinationId("destination_id")
                .workspaceId("workspace_id")
                .addBeta(AnthropicBeta.COMPUTER_USE_2024_10_22)
                .build()

        val headers = params._headers()

        assertThat(headers)
            .isEqualTo(
                Headers.builder()
                    .put(
                        "anthropic-beta",
                        listOf("computer-use-2024-10-22", "telemetry-destinations-2026-08-11")
                            .joinToString(","),
                    )
                    .build()
            )
    }

    @Test
    fun headersWithoutOptionalFields() {
        val params =
            WorkspaceRemoveParams.builder()
                .destinationId("destination_id")
                .workspaceId("workspace_id")
                .build()

        val headers = params._headers()

        assertThat(headers).isEqualTo(Headers.builder().build())
    }
}
