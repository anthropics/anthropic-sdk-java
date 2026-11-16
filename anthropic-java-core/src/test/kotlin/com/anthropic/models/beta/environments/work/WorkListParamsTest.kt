package com.anthropic.models.beta.environments.work

import com.anthropic.core.http.Headers
import com.anthropic.core.http.QueryParams
import com.anthropic.models.beta.AnthropicBeta
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class WorkListParamsTest {

    @Test
    fun create() {
        WorkListParams.builder()
            .environmentId("env_011CZkZ9X2dpNyB7HsEFoRfW")
            .limit(1L)
            .page("page")
            .addBeta(AnthropicBeta.COMPUTER_USE_2024_10_22)
            .build()
    }

    @Test
    fun pathParams() {
        val params = WorkListParams.builder().environmentId("env_011CZkZ9X2dpNyB7HsEFoRfW").build()

        assertThat(params._pathParam(0)).isEqualTo("env_011CZkZ9X2dpNyB7HsEFoRfW")
        // out-of-bound path param
        assertThat(params._pathParam(1)).isEqualTo("")
    }

    @Test
    fun headers() {
        val params =
            WorkListParams.builder()
                .environmentId("env_011CZkZ9X2dpNyB7HsEFoRfW")
                .limit(1L)
                .page("page")
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
        val params = WorkListParams.builder().environmentId("env_011CZkZ9X2dpNyB7HsEFoRfW").build()

        val headers = params._headers()

        assertThat(headers).isEqualTo(Headers.builder().build())
    }

    @Test
    fun queryParams() {
        val params =
            WorkListParams.builder()
                .environmentId("env_011CZkZ9X2dpNyB7HsEFoRfW")
                .limit(1L)
                .page("page")
                .addBeta(AnthropicBeta.COMPUTER_USE_2024_10_22)
                .build()

        val queryParams = params._queryParams()

        assertThat(queryParams)
            .isEqualTo(QueryParams.builder().put("limit", "1").put("page", "page").build())
    }

    @Test
    fun queryParamsWithoutOptionalFields() {
        val params = WorkListParams.builder().environmentId("env_011CZkZ9X2dpNyB7HsEFoRfW").build()

        val queryParams = params._queryParams()

        assertThat(queryParams).isEqualTo(QueryParams.builder().build())
    }
}
