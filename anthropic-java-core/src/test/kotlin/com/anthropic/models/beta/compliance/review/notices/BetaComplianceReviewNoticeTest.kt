package com.anthropic.models.beta.compliance.review.notices

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaComplianceReviewNoticeTest {

    @Test
    fun create() {
        val betaComplianceReviewNotice =
            BetaComplianceReviewNotice.builder()
                .id("id")
                .addCaseId("string")
                .createdAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .kind(
                    BetaComplianceReviewNoticeKind.of(
                        BetaComplianceReviewNoticeKind.Type.RESPONSE_REQUESTED
                    )
                )
                .message("message")
                .organizationId("organization_id")
                .respondBy(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .build()

        assertThat(betaComplianceReviewNotice.id()).isEqualTo("id")
        assertThat(betaComplianceReviewNotice.caseIds()).containsExactly("string")
        assertThat(betaComplianceReviewNotice.createdAt())
            .isEqualTo(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
        assertThat(betaComplianceReviewNotice.kind())
            .isEqualTo(
                BetaComplianceReviewNoticeKind.of(
                    BetaComplianceReviewNoticeKind.Type.RESPONSE_REQUESTED
                )
            )
        assertThat(betaComplianceReviewNotice.message()).isEqualTo("message")
        assertThat(betaComplianceReviewNotice.organizationId()).isEqualTo("organization_id")
        assertThat(betaComplianceReviewNotice.respondBy())
            .contains(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaComplianceReviewNotice =
            BetaComplianceReviewNotice.builder()
                .id("id")
                .addCaseId("string")
                .createdAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .kind(
                    BetaComplianceReviewNoticeKind.of(
                        BetaComplianceReviewNoticeKind.Type.RESPONSE_REQUESTED
                    )
                )
                .message("message")
                .organizationId("organization_id")
                .respondBy(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .build()

        val roundtrippedBetaComplianceReviewNotice =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaComplianceReviewNotice),
                jacksonTypeRef<BetaComplianceReviewNotice>(),
            )

        assertThat(roundtrippedBetaComplianceReviewNotice).isEqualTo(betaComplianceReviewNotice)
    }
}
