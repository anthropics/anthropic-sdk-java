package com.anthropic.models.beta.organization.workspaces

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaInferenceDataPrivacyTest {

    @Test
    fun create() {
        val betaInferenceDataPrivacy =
            BetaInferenceDataPrivacy.builder()
                .humanReview(BetaAnthropicHumanReview.of(BetaAnthropicHumanReview.Type.DISABLED))
                .safeguardsStorage(BetaInternalStorage.builder().build())
                .transcriptsStorage(BetaInternalStorage.builder().build())
                .build()

        assertThat(betaInferenceDataPrivacy.humanReview())
            .contains(BetaAnthropicHumanReview.of(BetaAnthropicHumanReview.Type.DISABLED))
        assertThat(betaInferenceDataPrivacy.safeguardsStorage())
            .contains(BetaStorageMember.ofInternal(BetaInternalStorage.builder().build()))
        assertThat(betaInferenceDataPrivacy.transcriptsStorage())
            .contains(BetaStorageMember.ofInternal(BetaInternalStorage.builder().build()))
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaInferenceDataPrivacy =
            BetaInferenceDataPrivacy.builder()
                .humanReview(BetaAnthropicHumanReview.of(BetaAnthropicHumanReview.Type.DISABLED))
                .safeguardsStorage(BetaInternalStorage.builder().build())
                .transcriptsStorage(BetaInternalStorage.builder().build())
                .build()

        val roundtrippedBetaInferenceDataPrivacy =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaInferenceDataPrivacy),
                jacksonTypeRef<BetaInferenceDataPrivacy>(),
            )

        assertThat(roundtrippedBetaInferenceDataPrivacy).isEqualTo(betaInferenceDataPrivacy)
    }
}
