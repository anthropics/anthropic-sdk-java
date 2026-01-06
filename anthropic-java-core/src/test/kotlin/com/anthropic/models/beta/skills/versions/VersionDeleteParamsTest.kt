// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.skills.versions

import com.anthropic.core.http.Headers
import com.anthropic.models.beta.AnthropicBeta
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class VersionDeleteParamsTest {

    @Test
    fun create() {
        VersionDeleteParams.builder()
            .skillId("skill_id")
            .version("version")
            .addBeta(AnthropicBeta.of("string"))
            .build()
    }

    @Test
    fun pathParams() {
        val params = VersionDeleteParams.builder().skillId("skill_id").version("version").build()

        assertThat(params._pathParam(0)).isEqualTo("skill_id")
        assertThat(params._pathParam(1)).isEqualTo("version")
        // out-of-bound path param
        assertThat(params._pathParam(2)).isEqualTo("")
    }

    @Test
    fun headers() {
        val params =
            VersionDeleteParams.builder()
                .skillId("skill_id")
                .version("version")
                .addBeta(AnthropicBeta.of("string"))
                .build()

        val headers = params._headers()

        assertThat(headers).isEqualTo(Headers.builder().put("anthropic-beta", "string").build())
    }

    @Test
    fun headersWithoutOptionalFields() {
        val params = VersionDeleteParams.builder().skillId("skill_id").version("version").build()

        val headers = params._headers()

        assertThat(headers).isEqualTo(Headers.builder().build())
    }
}
