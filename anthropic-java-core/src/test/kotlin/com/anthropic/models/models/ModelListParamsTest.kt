package com.anthropic.models.models

import com.anthropic.core.http.Headers
import com.anthropic.core.http.QueryParams
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ModelListParamsTest {

    @Test
    fun create() {
        ModelListParams.builder()
            .afterId("after_id")
            .beforeId("before_id")
            .addLifecycle(ModelListParams.Lifecycle.ACTIVE)
            .limit(1L)
            .workspaceId("wrkspc_011CZkZaBF1tNoB5wlCeusgy")
            .build()
    }

    @Test
    fun headers() {
        val params =
            ModelListParams.builder()
                .afterId("after_id")
                .beforeId("before_id")
                .addLifecycle(ModelListParams.Lifecycle.ACTIVE)
                .limit(1L)
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
        val params = ModelListParams.builder().build()

        val headers = params._headers()

        assertThat(headers).isEqualTo(Headers.builder().build())
    }

    @Test
    fun queryParams() {
        val params =
            ModelListParams.builder()
                .afterId("after_id")
                .beforeId("before_id")
                .addLifecycle(ModelListParams.Lifecycle.ACTIVE)
                .limit(1L)
                .workspaceId("wrkspc_011CZkZaBF1tNoB5wlCeusgy")
                .build()

        val queryParams = params._queryParams()

        assertThat(queryParams)
            .isEqualTo(
                QueryParams.builder()
                    .put("after_id", "after_id")
                    .put("before_id", "before_id")
                    .put("lifecycle[]", "active")
                    .put("limit", "1")
                    .build()
            )
    }

    @Test
    fun queryParamsWithoutOptionalFields() {
        val params = ModelListParams.builder().build()

        val queryParams = params._queryParams()

        assertThat(queryParams).isEqualTo(QueryParams.builder().build())
    }
}
