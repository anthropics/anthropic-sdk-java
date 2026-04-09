// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaAdvisorResultBlockParamTest {

    @Test
    fun create() {
        val betaAdvisorResultBlockParam = BetaAdvisorResultBlockParam.builder().text("text").build()

        assertThat(betaAdvisorResultBlockParam.text()).isEqualTo("text")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaAdvisorResultBlockParam = BetaAdvisorResultBlockParam.builder().text("text").build()

        val roundtrippedBetaAdvisorResultBlockParam =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaAdvisorResultBlockParam),
                jacksonTypeRef<BetaAdvisorResultBlockParam>(),
            )

        assertThat(roundtrippedBetaAdvisorResultBlockParam).isEqualTo(betaAdvisorResultBlockParam)
    }
}
