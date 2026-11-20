package com.anthropic.models.beta.organization.workspaces

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaAnthropicHumanReviewParamTest {

    @Test
    fun create() {
        val betaAnthropicHumanReviewParam =
            BetaAnthropicHumanReviewParam.of(BetaAnthropicHumanReviewParam.Type.DISABLED)

        assertThat(betaAnthropicHumanReviewParam.type())
            .isEqualTo(BetaAnthropicHumanReviewParam.Type.DISABLED)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaAnthropicHumanReviewParam =
            BetaAnthropicHumanReviewParam.of(BetaAnthropicHumanReviewParam.Type.DISABLED)

        val roundtrippedBetaAnthropicHumanReviewParam =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaAnthropicHumanReviewParam),
                jacksonTypeRef<BetaAnthropicHumanReviewParam>(),
            )

        assertThat(roundtrippedBetaAnthropicHumanReviewParam)
            .isEqualTo(betaAnthropicHumanReviewParam)
    }
}
