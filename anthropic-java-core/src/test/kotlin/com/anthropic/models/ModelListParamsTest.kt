// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models

import com.anthropic.core.http.QueryParams
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ModelListParamsTest {

    @Test
    fun createModelListParams() {
        ModelListParams.builder().afterId("after_id").beforeId("before_id").limit(1L).build()
    }

    @Test
    fun getQueryParams() {
        val params =
            ModelListParams.builder().afterId("after_id").beforeId("before_id").limit(1L).build()
        val expected = QueryParams.builder()
        expected.put("after_id", "after_id")
        expected.put("before_id", "before_id")
        expected.put("limit", "1")
        assertThat(params.getQueryParams()).isEqualTo(expected.build())
    }

    @Test
    fun getQueryParamsWithoutOptionalFields() {
        val params = ModelListParams.builder().build()
        val expected = QueryParams.builder()
        assertThat(params.getQueryParams()).isEqualTo(expected.build())
    }
}
