// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.skills

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class SkillRetrieveParamsTest {

    @Test
    fun create() {
        SkillRetrieveParams.builder().skillId("skill_id").build()
    }

    @Test
    fun pathParams() {
        val params = SkillRetrieveParams.builder().skillId("skill_id").build()

        assertThat(params._pathParam(0)).isEqualTo("skill_id")
        // out-of-bound path param
        assertThat(params._pathParam(1)).isEqualTo("")
    }
}
