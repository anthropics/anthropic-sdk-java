// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.organization.ratelimits

import com.anthropic.core.http.QueryParams
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class RateLimitListParamsTest {

    @Test
    fun create() {
        RateLimitListParams.builder()
            .groupType(RateLimitListParams.GroupType.BATCH)
            .limit(1L)
            .model("model")
            .page("page")
            .build()
    }

    @Test
    fun queryParams() {
        val params =
            RateLimitListParams.builder()
                .groupType(RateLimitListParams.GroupType.BATCH)
                .limit(1L)
                .model("model")
                .page("page")
                .build()

        val queryParams = params._queryParams()

        assertThat(queryParams)
            .isEqualTo(
                QueryParams.builder()
                    .put("group_type", "batch")
                    .put("limit", "1")
                    .put("model", "model")
                    .put("page", "page")
                    .build()
            )
    }

    @Test
    fun queryParamsWithoutOptionalFields() {
        val params = RateLimitListParams.builder().build()

        val queryParams = params._queryParams()

        assertThat(queryParams).isEqualTo(QueryParams.builder().build())
    }
}
