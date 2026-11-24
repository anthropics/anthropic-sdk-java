package com.anthropic.models.beta.compliance.review.notices

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class NoticeListPageResponseTest {

    @Test
    fun create() {
        val noticeListPageResponse =
            NoticeListPageResponse.builder()
                .addData(
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
                )
                .nextPage("next_page")
                .build()

        assertThat(noticeListPageResponse.data())
            .containsExactly(
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
            )
        assertThat(noticeListPageResponse.nextPage()).contains("next_page")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val noticeListPageResponse =
            NoticeListPageResponse.builder()
                .addData(
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
                )
                .nextPage("next_page")
                .build()

        val roundtrippedNoticeListPageResponse =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(noticeListPageResponse),
                jacksonTypeRef<NoticeListPageResponse>(),
            )

        assertThat(roundtrippedNoticeListPageResponse).isEqualTo(noticeListPageResponse)
    }
}
