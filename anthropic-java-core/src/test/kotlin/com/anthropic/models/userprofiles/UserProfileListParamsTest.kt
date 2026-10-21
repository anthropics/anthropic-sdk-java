package com.anthropic.models.userprofiles

import com.anthropic.core.http.Headers
import com.anthropic.core.http.QueryParams
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class UserProfileListParamsTest {

    @Test
    fun create() {
        UserProfileListParams.builder()
            .addEnforcementStatus(UserProfileEnforcementStatus.UNRESTRICTED)
            .limit(0)
            .order(UserProfileListOrder.ASC)
            .orderBy(UserProfileListOrderBy.CREATED_AT)
            .page("page")
            .workspaceId("wrkspc_011CZkZaBF1tNoB5wlCeusgy")
            .build()
    }

    @Test
    fun headers() {
        val params =
            UserProfileListParams.builder()
                .addEnforcementStatus(UserProfileEnforcementStatus.UNRESTRICTED)
                .limit(0)
                .order(UserProfileListOrder.ASC)
                .orderBy(UserProfileListOrderBy.CREATED_AT)
                .page("page")
                .workspaceId("wrkspc_011CZkZaBF1tNoB5wlCeusgy")
                .build()

        val headers = params._headers()

        assertThat(headers)
            .isEqualTo(
                Headers.builder()
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
                .addEnforcementStatus(UserProfileEnforcementStatus.UNRESTRICTED)
                .limit(0)
                .order(UserProfileListOrder.ASC)
                .orderBy(UserProfileListOrderBy.CREATED_AT)
                .page("page")
                .workspaceId("wrkspc_011CZkZaBF1tNoB5wlCeusgy")
                .build()

        val queryParams = params._queryParams()

        assertThat(queryParams)
            .isEqualTo(
                QueryParams.builder()
                    .put("enforcement_status[]", "unrestricted")
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
