// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.skills

import com.anthropic.core.http.Headers
import com.anthropic.models.beta.AnthropicBeta
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class SkillRetrieveParamsTest {

    @Test
    fun create() {
        SkillRetrieveParams.builder()
            .skillId("skill_id")
            .addBeta(AnthropicBeta.of("string"))
            .build()
    }

    @Test
    fun pathParams() {
        val params = SkillRetrieveParams.builder().skillId("skill_id").build()

        assertThat(params._pathParam(0)).isEqualTo("skill_id")
        // out-of-bound path param
        assertThat(params._pathParam(1)).isEqualTo("")
    }

    @Test
    fun headers() {
        val params =
            SkillRetrieveParams.builder()
                .skillId("skill_id")
                .addBeta(AnthropicBeta.of("string"))
                .build()

        val headers = params._headers()

        assertThat(headers).isEqualTo(Headers.builder().put("anthropic-beta", "string").build())
    }

    @Test
    fun headersWithoutOptionalFields() {
        val params = SkillRetrieveParams.builder().skillId("skill_id").build()

        val headers = params._headers()

        assertThat(headers).isEqualTo(Headers.builder().build())
    }
}
