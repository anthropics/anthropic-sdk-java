// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaAdvisorResultBlockTest {

    @Test
    fun create() {
        val betaAdvisorResultBlock = BetaAdvisorResultBlock.builder().text("text").build()

        assertThat(betaAdvisorResultBlock.text()).isEqualTo("text")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaAdvisorResultBlock = BetaAdvisorResultBlock.builder().text("text").build()

        val roundtrippedBetaAdvisorResultBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaAdvisorResultBlock),
                jacksonTypeRef<BetaAdvisorResultBlock>(),
            )

        assertThat(roundtrippedBetaAdvisorResultBlock).isEqualTo(betaAdvisorResultBlock)
    }
}
