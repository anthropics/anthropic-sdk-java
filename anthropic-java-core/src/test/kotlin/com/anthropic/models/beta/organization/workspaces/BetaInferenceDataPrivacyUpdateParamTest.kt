package com.anthropic.models.beta.organization.workspaces

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaInferenceDataPrivacyUpdateParamTest {

    @Test
    fun create() {
        val betaInferenceDataPrivacyUpdateParam =
            BetaInferenceDataPrivacyUpdateParam.builder()
                .humanReview(
                    BetaAnthropicHumanReviewParam.of(BetaAnthropicHumanReviewParam.Type.DISABLED)
                )
                .safeguardsStorage(BetaInternalStorageParam.builder().build())
                .transcriptsStorage(BetaInternalStorageParam.builder().build())
                .build()

        assertThat(betaInferenceDataPrivacyUpdateParam.humanReview())
            .contains(BetaAnthropicHumanReviewParam.of(BetaAnthropicHumanReviewParam.Type.DISABLED))
        assertThat(betaInferenceDataPrivacyUpdateParam.safeguardsStorage())
            .contains(BetaStorageMemberParam.ofInternal(BetaInternalStorageParam.builder().build()))
        assertThat(betaInferenceDataPrivacyUpdateParam.transcriptsStorage())
            .contains(BetaStorageMemberParam.ofInternal(BetaInternalStorageParam.builder().build()))
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaInferenceDataPrivacyUpdateParam =
            BetaInferenceDataPrivacyUpdateParam.builder()
                .humanReview(
                    BetaAnthropicHumanReviewParam.of(BetaAnthropicHumanReviewParam.Type.DISABLED)
                )
                .safeguardsStorage(BetaInternalStorageParam.builder().build())
                .transcriptsStorage(BetaInternalStorageParam.builder().build())
                .build()

        val roundtrippedBetaInferenceDataPrivacyUpdateParam =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaInferenceDataPrivacyUpdateParam),
                jacksonTypeRef<BetaInferenceDataPrivacyUpdateParam>(),
            )

        assertThat(roundtrippedBetaInferenceDataPrivacyUpdateParam)
            .isEqualTo(betaInferenceDataPrivacyUpdateParam)
    }
}
