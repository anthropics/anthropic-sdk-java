package com.anthropic.models.beta.organization.workspaces

import com.anthropic.core.http.Headers
import com.anthropic.core.http.QueryParams
import com.anthropic.models.beta.AnthropicBeta
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class WorkspaceListParamsTest {

    @Test
    fun create() {
        WorkspaceListParams.builder()
            .afterId("after_id")
            .beforeId("before_id")
            .addId("string")
            .includeArchived(true)
            .includeDefault(true)
            .limit(1L)
            .addBeta(AnthropicBeta.COMPUTER_USE_2024_10_22)
            .build()
    }

    @Test
    fun headers() {
        val params =
            WorkspaceListParams.builder()
                .afterId("after_id")
                .beforeId("before_id")
                .addId("string")
                .includeArchived(true)
                .includeDefault(true)
                .limit(1L)
                .addBeta(AnthropicBeta.COMPUTER_USE_2024_10_22)
                .build()

        val headers = params._headers()

        assertThat(headers)
            .isEqualTo(
                Headers.builder()
                    .put("anthropic-beta", listOf("computer-use-2024-10-22").joinToString(","))
                    .build()
            )
    }

    @Test
    fun headersWithoutOptionalFields() {
        val params = WorkspaceListParams.builder().build()

        val headers = params._headers()

        assertThat(headers).isEqualTo(Headers.builder().build())
    }

    @Test
    fun queryParams() {
        val params =
            WorkspaceListParams.builder()
                .afterId("after_id")
                .beforeId("before_id")
                .addId("string")
                .includeArchived(true)
                .includeDefault(true)
                .limit(1L)
                .addBeta(AnthropicBeta.COMPUTER_USE_2024_10_22)
                .build()

        val queryParams = params._queryParams()

        assertThat(queryParams)
            .isEqualTo(
                QueryParams.builder()
                    .put("after_id", "after_id")
                    .put("before_id", "before_id")
                    .put("ids[]", "string")
                    .put("include_archived", "true")
                    .put("include_default", "true")
                    .put("limit", "1")
                    .build()
            )
    }

    @Test
    fun queryParamsWithoutOptionalFields() {
        val params = WorkspaceListParams.builder().build()

        val queryParams = params._queryParams()

        assertThat(queryParams).isEqualTo(QueryParams.builder().build())
    }
}
