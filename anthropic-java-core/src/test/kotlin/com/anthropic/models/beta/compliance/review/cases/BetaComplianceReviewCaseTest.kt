package com.anthropic.models.beta.compliance.review.cases

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaComplianceReviewCaseTest {

    @Test
    fun create() {
        val betaComplianceReviewCase =
            BetaComplianceReviewCase.builder()
                .id("id")
                .createdAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .enforcementAction(BetaComplianceReviewEnforcementAction.of("type"))
                .organizationId("organization_id")
                .addPolicyCategory(BetaComplianceReviewPolicyCategory.CYBER)
                .status(BetaComplianceReviewCaseOpen.builder().build())
                .addTranscript(
                    BetaComplianceReviewTranscript.builder()
                        .itemId("item_id")
                        .userProfileId("user_profile_id")
                        .build()
                )
                .updatedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .workspaceId("workspace_id")
                .build()

        assertThat(betaComplianceReviewCase.id()).isEqualTo("id")
        assertThat(betaComplianceReviewCase.createdAt())
            .isEqualTo(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
        assertThat(betaComplianceReviewCase.enforcementAction())
            .contains(BetaComplianceReviewEnforcementAction.of("type"))
        assertThat(betaComplianceReviewCase.organizationId()).isEqualTo("organization_id")
        assertThat(betaComplianceReviewCase.policyCategories())
            .containsExactly(BetaComplianceReviewPolicyCategory.CYBER)
        assertThat(betaComplianceReviewCase.status())
            .isEqualTo(
                BetaComplianceReviewCaseStatus.ofOpen(
                    BetaComplianceReviewCaseOpen.builder().build()
                )
            )
        assertThat(betaComplianceReviewCase.transcripts())
            .containsExactly(
                BetaComplianceReviewTranscript.builder()
                    .itemId("item_id")
                    .userProfileId("user_profile_id")
                    .build()
            )
        assertThat(betaComplianceReviewCase.updatedAt())
            .isEqualTo(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
        assertThat(betaComplianceReviewCase.workspaceId()).contains("workspace_id")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaComplianceReviewCase =
            BetaComplianceReviewCase.builder()
                .id("id")
                .createdAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .enforcementAction(BetaComplianceReviewEnforcementAction.of("type"))
                .organizationId("organization_id")
                .addPolicyCategory(BetaComplianceReviewPolicyCategory.CYBER)
                .status(BetaComplianceReviewCaseOpen.builder().build())
                .addTranscript(
                    BetaComplianceReviewTranscript.builder()
                        .itemId("item_id")
                        .userProfileId("user_profile_id")
                        .build()
                )
                .updatedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .workspaceId("workspace_id")
                .build()

        val roundtrippedBetaComplianceReviewCase =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaComplianceReviewCase),
                jacksonTypeRef<BetaComplianceReviewCase>(),
            )

        assertThat(roundtrippedBetaComplianceReviewCase).isEqualTo(betaComplianceReviewCase)
    }
}
