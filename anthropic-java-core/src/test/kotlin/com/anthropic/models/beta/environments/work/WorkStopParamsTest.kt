package com.anthropic.models.beta.environments.work

import com.anthropic.core.http.Headers
import com.anthropic.models.beta.AnthropicBeta
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class WorkStopParamsTest {

    @Test
    fun create() {
        WorkStopParams.builder()
            .environmentId("env_011CZkZ9X2dpNyB7HsEFoRfW")
            .workId("work_id")
            .addBeta(AnthropicBeta.COMPUTER_USE_2024_10_22)
            .workspaceId("wrkspc_011CZkZaBF1tNoB5wlCeusgy")
            .betaSelfHostedWorkStopRequest(
                BetaSelfHostedWorkStopRequest.builder().force(true).build()
            )
            .build()
    }

    @Test
    fun pathParams() {
        val params =
            WorkStopParams.builder()
                .environmentId("env_011CZkZ9X2dpNyB7HsEFoRfW")
                .workId("work_id")
                .betaSelfHostedWorkStopRequest(BetaSelfHostedWorkStopRequest.builder().build())
                .build()

        assertThat(params._pathParam(0)).isEqualTo("env_011CZkZ9X2dpNyB7HsEFoRfW")
        assertThat(params._pathParam(1)).isEqualTo("work_id")
        // out-of-bound path param
        assertThat(params._pathParam(2)).isEqualTo("")
    }

    @Test
    fun headers() {
        val params =
            WorkStopParams.builder()
                .environmentId("env_011CZkZ9X2dpNyB7HsEFoRfW")
                .workId("work_id")
                .addBeta(AnthropicBeta.COMPUTER_USE_2024_10_22)
                .workspaceId("wrkspc_011CZkZaBF1tNoB5wlCeusgy")
                .betaSelfHostedWorkStopRequest(
                    BetaSelfHostedWorkStopRequest.builder().force(true).build()
                )
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
            WorkStopParams.builder()
                .environmentId("env_011CZkZ9X2dpNyB7HsEFoRfW")
                .workId("work_id")
                .betaSelfHostedWorkStopRequest(BetaSelfHostedWorkStopRequest.builder().build())
                .build()

        val headers = params._headers()

        assertThat(headers).isEqualTo(Headers.builder().build())
    }

    @Test
    fun body() {
        val params =
            WorkStopParams.builder()
                .environmentId("env_011CZkZ9X2dpNyB7HsEFoRfW")
                .workId("work_id")
                .addBeta(AnthropicBeta.COMPUTER_USE_2024_10_22)
                .workspaceId("wrkspc_011CZkZaBF1tNoB5wlCeusgy")
                .betaSelfHostedWorkStopRequest(
                    BetaSelfHostedWorkStopRequest.builder().force(true).build()
                )
                .build()

        val body = params._body()

        assertThat(body).isEqualTo(BetaSelfHostedWorkStopRequest.builder().force(true).build())
    }

    @Test
    fun bodyWithoutOptionalFields() {
        val params =
            WorkStopParams.builder()
                .environmentId("env_011CZkZ9X2dpNyB7HsEFoRfW")
                .workId("work_id")
                .betaSelfHostedWorkStopRequest(BetaSelfHostedWorkStopRequest.builder().build())
                .build()

        val body = params._body()

        assertThat(body).isEqualTo(BetaSelfHostedWorkStopRequest.builder().build())
    }
}
