package com.anthropic.models.beta.environments.work

import com.anthropic.core.http.Headers
import com.anthropic.core.http.QueryParams
import com.anthropic.models.beta.AnthropicBeta
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class WorkHeartbeatParamsTest {

    @Test
    fun create() {
        WorkHeartbeatParams.builder()
            .environmentId("env_011CZkZ9X2dpNyB7HsEFoRfW")
            .workId("work_id")
            .desiredTtlSeconds(0L)
            .expectedLastHeartbeat("expected_last_heartbeat")
            .addBeta(AnthropicBeta.COMPUTER_USE_2024_10_22)
            .build()
    }

    @Test
    fun pathParams() {
        val params =
            WorkHeartbeatParams.builder()
                .environmentId("env_011CZkZ9X2dpNyB7HsEFoRfW")
                .workId("work_id")
                .build()

        assertThat(params._pathParam(0)).isEqualTo("env_011CZkZ9X2dpNyB7HsEFoRfW")
        assertThat(params._pathParam(1)).isEqualTo("work_id")
        // out-of-bound path param
        assertThat(params._pathParam(2)).isEqualTo("")
    }

    @Test
    fun headers() {
        val params =
            WorkHeartbeatParams.builder()
                .environmentId("env_011CZkZ9X2dpNyB7HsEFoRfW")
                .workId("work_id")
                .desiredTtlSeconds(0L)
                .expectedLastHeartbeat("expected_last_heartbeat")
                .addBeta(AnthropicBeta.COMPUTER_USE_2024_10_22)
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
                    .build()
            )
    }

    @Test
    fun headersWithoutOptionalFields() {
        val params =
            WorkHeartbeatParams.builder()
                .environmentId("env_011CZkZ9X2dpNyB7HsEFoRfW")
                .workId("work_id")
                .build()

        val headers = params._headers()

        assertThat(headers).isEqualTo(Headers.builder().build())
    }

    @Test
    fun queryParams() {
        val params =
            WorkHeartbeatParams.builder()
                .environmentId("env_011CZkZ9X2dpNyB7HsEFoRfW")
                .workId("work_id")
                .desiredTtlSeconds(0L)
                .expectedLastHeartbeat("expected_last_heartbeat")
                .addBeta(AnthropicBeta.COMPUTER_USE_2024_10_22)
                .build()

        val queryParams = params._queryParams()

        assertThat(queryParams)
            .isEqualTo(
                QueryParams.builder()
                    .put("desired_ttl_seconds", "0")
                    .put("expected_last_heartbeat", "expected_last_heartbeat")
                    .build()
            )
    }

    @Test
    fun queryParamsWithoutOptionalFields() {
        val params =
            WorkHeartbeatParams.builder()
                .environmentId("env_011CZkZ9X2dpNyB7HsEFoRfW")
                .workId("work_id")
                .build()

        val queryParams = params._queryParams()

        assertThat(queryParams).isEqualTo(QueryParams.builder().build())
    }
}
