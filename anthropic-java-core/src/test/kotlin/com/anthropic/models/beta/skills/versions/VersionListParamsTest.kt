// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.skills.versions

import com.anthropic.core.http.Headers
import com.anthropic.core.http.QueryParams
import com.anthropic.models.beta.AnthropicBeta
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class VersionListParamsTest {

    @Test
    fun create() {
        VersionListParams.builder()
            .skillId("skill_id")
            .limit(0L)
            .page("page")
            .addBeta(AnthropicBeta.of("string"))
            .build()
    }

    @Test
    fun pathParams() {
        val params = VersionListParams.builder().skillId("skill_id").build()

        assertThat(params._pathParam(0)).isEqualTo("skill_id")
        // out-of-bound path param
        assertThat(params._pathParam(1)).isEqualTo("")
    }

    @Test
    fun headers() {
        val params =
            VersionListParams.builder()
                .skillId("skill_id")
                .limit(0L)
                .page("page")
                .addBeta(AnthropicBeta.of("string"))
                .build()

        val headers = params._headers()

        assertThat(headers).isEqualTo(Headers.builder().put("anthropic-beta", "string").build())
    }

    @Test
    fun headersWithoutOptionalFields() {
        val params = VersionListParams.builder().skillId("skill_id").build()

        val headers = params._headers()

        assertThat(headers).isEqualTo(Headers.builder().build())
    }

    @Test
    fun queryParams() {
        val params =
            VersionListParams.builder()
                .skillId("skill_id")
                .limit(0L)
                .page("page")
                .addBeta(AnthropicBeta.of("string"))
                .build()

        val queryParams = params._queryParams()

        assertThat(queryParams)
            .isEqualTo(QueryParams.builder().put("limit", "0").put("page", "page").build())
    }

    @Test
    fun queryParamsWithoutOptionalFields() {
        val params = VersionListParams.builder().skillId("skill_id").build()

        val queryParams = params._queryParams()

        assertThat(queryParams).isEqualTo(QueryParams.builder().build())
    }
}
