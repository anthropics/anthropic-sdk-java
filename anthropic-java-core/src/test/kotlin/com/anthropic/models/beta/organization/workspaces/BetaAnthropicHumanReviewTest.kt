package com.anthropic.models.beta.organization.workspaces

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaAnthropicHumanReviewTest {

    @Test
    fun create() {
        val betaAnthropicHumanReview =
            BetaAnthropicHumanReview.of(BetaAnthropicHumanReview.Type.DISABLED)

        assertThat(betaAnthropicHumanReview.type())
            .isEqualTo(BetaAnthropicHumanReview.Type.DISABLED)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaAnthropicHumanReview =
            BetaAnthropicHumanReview.of(BetaAnthropicHumanReview.Type.DISABLED)

        val roundtrippedBetaAnthropicHumanReview =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaAnthropicHumanReview),
                jacksonTypeRef<BetaAnthropicHumanReview>(),
            )

        assertThat(roundtrippedBetaAnthropicHumanReview).isEqualTo(betaAnthropicHumanReview)
    }
}
