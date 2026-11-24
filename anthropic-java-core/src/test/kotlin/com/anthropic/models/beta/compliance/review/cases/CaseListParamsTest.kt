package com.anthropic.models.beta.compliance.review.cases

import com.anthropic.core.http.Headers
import com.anthropic.core.http.QueryParams
import com.anthropic.models.beta.AnthropicBeta
import com.anthropic.models.beta.compliance.review.BetaComplianceReviewOrder
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class CaseListParamsTest {

    @Test
    fun create() {
        CaseListParams.builder()
            .createdAtGt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
            .createdAtGte(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
            .createdAtLt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
            .createdAtLte(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
            .limit(0)
            .order(BetaComplianceReviewOrder.ASC)
            .orderBy(BetaComplianceReviewCaseOrderBy.CREATED_AT)
            .page("page")
            .status(BetaComplianceReviewCaseStatusFilter.OPEN)
            .updatedAtGt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
            .updatedAtGte(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
            .updatedAtLt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
            .updatedAtLte(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
            .userProfileId("user_profile_id")
            .addBeta(AnthropicBeta.COMPUTER_USE_2024_10_22)
            .build()
    }

    @Test
    fun headers() {
        val params =
            CaseListParams.builder()
                .createdAtGt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .createdAtGte(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .createdAtLt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .createdAtLte(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .limit(0)
                .order(BetaComplianceReviewOrder.ASC)
                .orderBy(BetaComplianceReviewCaseOrderBy.CREATED_AT)
                .page("page")
                .status(BetaComplianceReviewCaseStatusFilter.OPEN)
                .updatedAtGt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .updatedAtGte(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .updatedAtLt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .updatedAtLte(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .userProfileId("user_profile_id")
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
        val params = CaseListParams.builder().build()

        val headers = params._headers()

        assertThat(headers).isEqualTo(Headers.builder().build())
    }

    @Test
    fun queryParams() {
        val params =
            CaseListParams.builder()
                .createdAtGt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .createdAtGte(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .createdAtLt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .createdAtLte(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .limit(0)
                .order(BetaComplianceReviewOrder.ASC)
                .orderBy(BetaComplianceReviewCaseOrderBy.CREATED_AT)
                .page("page")
                .status(BetaComplianceReviewCaseStatusFilter.OPEN)
                .updatedAtGt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .updatedAtGte(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .updatedAtLt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .updatedAtLte(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .userProfileId("user_profile_id")
                .addBeta(AnthropicBeta.COMPUTER_USE_2024_10_22)
                .build()

        val queryParams = params._queryParams()

        assertThat(queryParams)
            .isEqualTo(
                QueryParams.builder()
                    .put("created_at[gt]", "2019-12-27T18:11:19.117Z")
                    .put("created_at[gte]", "2019-12-27T18:11:19.117Z")
                    .put("created_at[lt]", "2019-12-27T18:11:19.117Z")
                    .put("created_at[lte]", "2019-12-27T18:11:19.117Z")
                    .put("limit", "0")
                    .put("order", "asc")
                    .put("order_by", "created_at")
                    .put("page", "page")
                    .put("status", "open")
                    .put("updated_at[gt]", "2019-12-27T18:11:19.117Z")
                    .put("updated_at[gte]", "2019-12-27T18:11:19.117Z")
                    .put("updated_at[lt]", "2019-12-27T18:11:19.117Z")
                    .put("updated_at[lte]", "2019-12-27T18:11:19.117Z")
                    .put("user_profile_id", "user_profile_id")
                    .build()
            )
    }

    @Test
    fun queryParamsWithoutOptionalFields() {
        val params = CaseListParams.builder().build()

        val queryParams = params._queryParams()

        assertThat(queryParams).isEqualTo(QueryParams.builder().build())
    }
}
