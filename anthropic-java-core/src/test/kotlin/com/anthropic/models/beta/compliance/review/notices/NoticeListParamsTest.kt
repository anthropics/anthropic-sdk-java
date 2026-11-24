package com.anthropic.models.beta.compliance.review.notices

import com.anthropic.core.http.Headers
import com.anthropic.core.http.QueryParams
import com.anthropic.models.beta.AnthropicBeta
import com.anthropic.models.beta.compliance.review.BetaComplianceReviewOrder
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class NoticeListParamsTest {

    @Test
    fun create() {
        NoticeListParams.builder()
            .caseId("case_id")
            .createdAtGt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
            .createdAtGte(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
            .createdAtLt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
            .createdAtLte(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
            .limit(0)
            .order(BetaComplianceReviewOrder.ASC)
            .page("page")
            .addBeta(AnthropicBeta.COMPUTER_USE_2024_10_22)
            .build()
    }

    @Test
    fun headers() {
        val params =
            NoticeListParams.builder()
                .caseId("case_id")
                .createdAtGt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .createdAtGte(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .createdAtLt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .createdAtLte(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .limit(0)
                .order(BetaComplianceReviewOrder.ASC)
                .page("page")
                .addBeta(AnthropicBeta.COMPUTER_USE_2024_10_22)
                .build()

        val headers = params._headers()

        assertThat(headers)
            .isEqualTo(
                Headers.builder()
                    .put("anthropic-beta", listOf("computer-use-2024-10-22").joinToString(","))
                    .build()
            )
    }

    @Test
    fun headersWithoutOptionalFields() {
        val params = NoticeListParams.builder().build()

        val headers = params._headers()

        assertThat(headers).isEqualTo(Headers.builder().build())
    }

    @Test
    fun queryParams() {
        val params =
            NoticeListParams.builder()
                .caseId("case_id")
                .createdAtGt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .createdAtGte(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .createdAtLt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .createdAtLte(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .limit(0)
                .order(BetaComplianceReviewOrder.ASC)
                .page("page")
                .addBeta(AnthropicBeta.COMPUTER_USE_2024_10_22)
                .build()

        val queryParams = params._queryParams()

        assertThat(queryParams)
            .isEqualTo(
                QueryParams.builder()
                    .put("case_id", "case_id")
                    .put("created_at[gt]", "2019-12-27T18:11:19.117Z")
                    .put("created_at[gte]", "2019-12-27T18:11:19.117Z")
                    .put("created_at[lt]", "2019-12-27T18:11:19.117Z")
                    .put("created_at[lte]", "2019-12-27T18:11:19.117Z")
                    .put("limit", "0")
                    .put("order", "asc")
                    .put("page", "page")
                    .build()
            )
    }

    @Test
    fun queryParamsWithoutOptionalFields() {
        val params = NoticeListParams.builder().build()

        val queryParams = params._queryParams()

        assertThat(queryParams).isEqualTo(QueryParams.builder().build())
    }
}
