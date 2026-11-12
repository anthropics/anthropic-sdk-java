package com.anthropic.models.beta.userprofiles

import com.anthropic.core.http.Headers
import com.anthropic.core.http.QueryParams
import com.anthropic.models.beta.AnthropicBeta
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class UserProfileListParamsTest {

    @Test
    fun create() {
        UserProfileListParams.builder()
            .limit(0)
            .order(UserProfileListParams.Order.ASC)
            .orderBy(UserProfileListParams.OrderBy.CREATED_AT)
            .page("page")
            .addBeta(AnthropicBeta.COMPUTER_USE_2024_10_22)
            .workspaceId("wrkspc_011CZkZaBF1tNoB5wlCeusgy")
            .build()
    }

    @Test
    fun headers() {
        val params =
            UserProfileListParams.builder()
                .limit(0)
                .order(UserProfileListParams.Order.ASC)
                .orderBy(UserProfileListParams.OrderBy.CREATED_AT)
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
                        listOf("computer-use-2024-10-22", "user-profiles-2026-08-18")
                            .joinToString(","),
                    )
                    .put("anthropic-workspace-id", "wrkspc_011CZkZaBF1tNoB5wlCeusgy")
                    .build()
            )
    }

    @Test
    fun headersWithoutOptionalFields() {
        val params = UserProfileListParams.builder().build()

        val headers = params._headers()

        assertThat(headers).isEqualTo(Headers.builder().build())
    }

    @Test
    fun queryParams() {
        val params =
            UserProfileListParams.builder()
                .limit(0)
                .order(UserProfileListParams.Order.ASC)
                .orderBy(UserProfileListParams.OrderBy.CREATED_AT)
                .page("page")
                .addBeta(AnthropicBeta.COMPUTER_USE_2024_10_22)
                .workspaceId("wrkspc_011CZkZaBF1tNoB5wlCeusgy")
                .build()

        val queryParams = params._queryParams()

        assertThat(queryParams)
            .isEqualTo(
                QueryParams.builder()
                    .put("limit", "0")
                    .put("order", "asc")
                    .put("order_by", "created_at")
                    .put("page", "page")
                    .build()
            )
    }

    @Test
    fun queryParamsWithoutOptionalFields() {
        val params = UserProfileListParams.builder().build()

        val queryParams = params._queryParams()

        assertThat(queryParams).isEqualTo(QueryParams.builder().build())
    }
}
