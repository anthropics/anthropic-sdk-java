package com.anthropic.models.beta.vaults.credentials

import com.anthropic.core.http.Headers
import com.anthropic.core.http.QueryParams
import com.anthropic.models.beta.AnthropicBeta
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class CredentialListParamsTest {

    @Test
    fun create() {
        CredentialListParams.builder()
            .vaultId("vlt_011CZkZDLs7fYzm1hXNPeRjv")
            .includeArchived(true)
            .limit(0)
            .page("page")
            .addBeta(AnthropicBeta.COMPUTER_USE_2024_10_22)
            .workspaceId("wrkspc_011CZkZaBF1tNoB5wlCeusgy")
            .build()
    }

    @Test
    fun pathParams() {
        val params = CredentialListParams.builder().vaultId("vlt_011CZkZDLs7fYzm1hXNPeRjv").build()

        assertThat(params._pathParam(0)).isEqualTo("vlt_011CZkZDLs7fYzm1hXNPeRjv")
        // out-of-bound path param
        assertThat(params._pathParam(1)).isEqualTo("")
    }

    @Test
    fun headers() {
        val params =
            CredentialListParams.builder()
                .vaultId("vlt_011CZkZDLs7fYzm1hXNPeRjv")
                .includeArchived(true)
                .limit(0)
                .page("page")
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
        val params = CredentialListParams.builder().vaultId("vlt_011CZkZDLs7fYzm1hXNPeRjv").build()

        val headers = params._headers()

        assertThat(headers).isEqualTo(Headers.builder().build())
    }

    @Test
    fun queryParams() {
        val params =
            CredentialListParams.builder()
                .vaultId("vlt_011CZkZDLs7fYzm1hXNPeRjv")
                .includeArchived(true)
                .limit(0)
                .page("page")
                .addBeta(AnthropicBeta.COMPUTER_USE_2024_10_22)
                .workspaceId("wrkspc_011CZkZaBF1tNoB5wlCeusgy")
                .build()

        val queryParams = params._queryParams()

        assertThat(queryParams)
            .isEqualTo(
                QueryParams.builder()
                    .put("include_archived", "true")
                    .put("limit", "0")
                    .put("page", "page")
                    .build()
            )
    }

    @Test
    fun queryParamsWithoutOptionalFields() {
        val params = CredentialListParams.builder().vaultId("vlt_011CZkZDLs7fYzm1hXNPeRjv").build()

        val queryParams = params._queryParams()

        assertThat(queryParams).isEqualTo(QueryParams.builder().build())
    }
}
