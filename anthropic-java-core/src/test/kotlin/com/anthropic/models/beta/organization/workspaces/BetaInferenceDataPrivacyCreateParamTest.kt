package com.anthropic.models.beta.organization.workspaces

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaInferenceDataPrivacyCreateParamTest {

    @Test
    fun create() {
        val betaInferenceDataPrivacyCreateParam =
            BetaInferenceDataPrivacyCreateParam.builder()
                .humanReview(
                    BetaAnthropicHumanReviewParam.of(BetaAnthropicHumanReviewParam.Type.DISABLED)
                )
                .safeguardsStorage(BetaInternalStorageParam.builder().build())
                .transcriptsStorage(BetaInternalStorageParam.builder().build())
                .build()

        assertThat(betaInferenceDataPrivacyCreateParam.humanReview())
            .contains(BetaAnthropicHumanReviewParam.of(BetaAnthropicHumanReviewParam.Type.DISABLED))
        assertThat(betaInferenceDataPrivacyCreateParam.safeguardsStorage())
            .contains(BetaStorageMemberParam.ofInternal(BetaInternalStorageParam.builder().build()))
        assertThat(betaInferenceDataPrivacyCreateParam.transcriptsStorage())
            .contains(BetaStorageMemberParam.ofInternal(BetaInternalStorageParam.builder().build()))
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaInferenceDataPrivacyCreateParam =
            BetaInferenceDataPrivacyCreateParam.builder()
                .humanReview(
                    BetaAnthropicHumanReviewParam.of(BetaAnthropicHumanReviewParam.Type.DISABLED)
                )
                .safeguardsStorage(BetaInternalStorageParam.builder().build())
                .transcriptsStorage(BetaInternalStorageParam.builder().build())
                .build()

        val roundtrippedBetaInferenceDataPrivacyCreateParam =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaInferenceDataPrivacyCreateParam),
                jacksonTypeRef<BetaInferenceDataPrivacyCreateParam>(),
            )

        assertThat(roundtrippedBetaInferenceDataPrivacyCreateParam)
            .isEqualTo(betaInferenceDataPrivacyCreateParam)
    }
}
