package com.anthropic.models.beta.organization.workspaces

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaWorkspaceChangedConcurrentlyErrorDetailsTest {

    @Test
    fun create() {
        val betaWorkspaceChangedConcurrentlyErrorDetails =
            BetaWorkspaceChangedConcurrentlyErrorDetails.of(
                BetaWorkspaceChangedConcurrentlyField.INFERENCE_DATA_PRIVACY_HUMAN_REVIEW
            )

        assertThat(betaWorkspaceChangedConcurrentlyErrorDetails.field())
            .isEqualTo(BetaWorkspaceChangedConcurrentlyField.INFERENCE_DATA_PRIVACY_HUMAN_REVIEW)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaWorkspaceChangedConcurrentlyErrorDetails =
            BetaWorkspaceChangedConcurrentlyErrorDetails.of(
                BetaWorkspaceChangedConcurrentlyField.INFERENCE_DATA_PRIVACY_HUMAN_REVIEW
            )

        val roundtrippedBetaWorkspaceChangedConcurrentlyErrorDetails =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaWorkspaceChangedConcurrentlyErrorDetails),
                jacksonTypeRef<BetaWorkspaceChangedConcurrentlyErrorDetails>(),
            )

        assertThat(roundtrippedBetaWorkspaceChangedConcurrentlyErrorDetails)
            .isEqualTo(betaWorkspaceChangedConcurrentlyErrorDetails)
    }
}
