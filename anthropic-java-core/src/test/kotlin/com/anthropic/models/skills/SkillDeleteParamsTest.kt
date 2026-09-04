// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.skills

import com.anthropic.core.http.Headers
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class SkillDeleteParamsTest {

    @Test
    fun create() {
        SkillDeleteParams.builder()
            .skillId("skill_id")
            .workspaceId("wrkspc_011CZkZaBF1tNoB5wlCeusgy")
            .build()
    }

    @Test
    fun pathParams() {
        val params = SkillDeleteParams.builder().skillId("skill_id").build()

        assertThat(params._pathParam(0)).isEqualTo("skill_id")
        // out-of-bound path param
        assertThat(params._pathParam(1)).isEqualTo("")
    }

    @Test
    fun headers() {
        val params =
            SkillDeleteParams.builder()
                .skillId("skill_id")
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
        val params = SkillDeleteParams.builder().skillId("skill_id").build()

        val headers = params._headers()

        assertThat(headers).isEqualTo(Headers.builder().build())
    }
}
