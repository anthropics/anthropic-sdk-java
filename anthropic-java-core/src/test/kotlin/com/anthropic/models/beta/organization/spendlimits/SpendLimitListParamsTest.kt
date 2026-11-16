package com.anthropic.models.beta.organization.spendlimits

import com.anthropic.core.http.Headers
import com.anthropic.core.http.QueryParams
import com.anthropic.models.beta.AnthropicBeta
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class SpendLimitListParamsTest {

    @Test
    fun create() {
        SpendLimitListParams.builder()
            .limit(1L)
            .page("page")
            .addScopeType(SpendLimitListParams.ScopeType.ORGANIZATION)
            .workspaceId("workspace_id")
            .addBeta(AnthropicBeta.COMPUTER_USE_2024_10_22)
            .build()
    }

    @Test
    fun headers() {
        val params =
            SpendLimitListParams.builder()
                .limit(1L)
                .page("page")
                .addScopeType(SpendLimitListParams.ScopeType.ORGANIZATION)
                .workspaceId("workspace_id")
                .addBeta(AnthropicBeta.COMPUTER_USE_2024_10_22)
                .build()

        val headers = params._headers()

        assertThat(headers)
            .isEqualTo(
                Headers.builder()
                    .put(
                        "anthropic-beta",
                        listOf("computer-use-2024-10-22", "spend-limit-reads-2026-09-26")
                            .joinToString(","),
                    )
                    .build()
            )
    }

    @Test
    fun headersWithoutOptionalFields() {
        val params = SpendLimitListParams.builder().build()

        val headers = params._headers()

        assertThat(headers).isEqualTo(Headers.builder().build())
    }

    @Test
    fun queryParams() {
        val params =
            SpendLimitListParams.builder()
                .limit(1L)
                .page("page")
                .addScopeType(SpendLimitListParams.ScopeType.ORGANIZATION)
                .workspaceId("workspace_id")
                .addBeta(AnthropicBeta.COMPUTER_USE_2024_10_22)
                .build()

        val queryParams = params._queryParams()

        assertThat(queryParams)
            .isEqualTo(
                QueryParams.builder()
                    .put("limit", "1")
                    .put("page", "page")
                    .put("scope_type[]", "organization")
                    .put("workspace_id", "workspace_id")
                    .build()
            )
    }

    @Test
    fun queryParamsWithoutOptionalFields() {
        val params = SpendLimitListParams.builder().build()

        val queryParams = params._queryParams()

        assertThat(queryParams).isEqualTo(QueryParams.builder().build())
    }
}
