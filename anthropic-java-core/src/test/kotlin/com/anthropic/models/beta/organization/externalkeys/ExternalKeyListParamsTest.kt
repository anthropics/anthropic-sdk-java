// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.organization.externalkeys

import com.anthropic.core.http.QueryParams
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ExternalKeyListParamsTest {

    @Test
    fun create() {
        ExternalKeyListParams.builder().limit(1L).page("page").build()
    }

    @Test
    fun queryParams() {
        val params = ExternalKeyListParams.builder().limit(1L).page("page").build()

        val queryParams = params._queryParams()

        assertThat(queryParams)
            .isEqualTo(QueryParams.builder().put("limit", "1").put("page", "page").build())
    }

    @Test
    fun queryParamsWithoutOptionalFields() {
        val params = ExternalKeyListParams.builder().build()

        val queryParams = params._queryParams()

        assertThat(queryParams).isEqualTo(QueryParams.builder().build())
    }
}
