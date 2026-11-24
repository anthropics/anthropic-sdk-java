package com.anthropic.models.beta.compliance.review.cases

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class CaseListPageResponseTest {

    @Test
    fun create() {
        val caseListPageResponse =
            CaseListPageResponse.builder()
                .addData(
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
                )
                .nextPage("next_page")
                .build()

        assertThat(caseListPageResponse.data())
            .containsExactly(
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
            )
        assertThat(caseListPageResponse.nextPage()).contains("next_page")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val caseListPageResponse =
            CaseListPageResponse.builder()
                .addData(
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
                )
                .nextPage("next_page")
                .build()

        val roundtrippedCaseListPageResponse =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(caseListPageResponse),
                jacksonTypeRef<CaseListPageResponse>(),
            )

        assertThat(roundtrippedCaseListPageResponse).isEqualTo(caseListPageResponse)
    }
}
