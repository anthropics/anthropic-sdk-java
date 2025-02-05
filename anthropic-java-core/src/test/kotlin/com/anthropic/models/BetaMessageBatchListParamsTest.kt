// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models

import com.anthropic.core.http.QueryParams
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BetaMessageBatchListParamsTest {

    @Test
    fun create() {
        BetaMessageBatchListParams.builder()
            .afterId("after_id")
            .beforeId("before_id")
            .limit(1L)
            .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
            .build()
    }

    @Test
    fun queryParams() {
        val params =
            BetaMessageBatchListParams.builder()
                .afterId("after_id")
                .beforeId("before_id")
                .limit(1L)
                .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                .build()
        val expected = QueryParams.builder()
        expected.put("after_id", "after_id")
        expected.put("before_id", "before_id")
        expected.put("limit", "1")
        assertThat(params._queryParams()).isEqualTo(expected.build())
    }

    @Test
    fun queryParamsWithoutOptionalFields() {
        val params = BetaMessageBatchListParams.builder().build()
        val expected = QueryParams.builder()
        assertThat(params._queryParams()).isEqualTo(expected.build())
    }
}
