package com.anthropic.models.beta.organization.spendlimits.increaserequests

import com.anthropic.core.http.QueryParams
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class IncreaseRequestListParamsTest {

    @Test
    fun create() {
        IncreaseRequestListParams.builder()
            .addActorId("string")
            .limit(1L)
            .page("page")
            .addStatus(BetaSpendLimitIncreaseRequestStatus.APPROVED)
            .build()
    }

    @Test
    fun queryParams() {
        val params =
            IncreaseRequestListParams.builder()
                .addActorId("string")
                .limit(1L)
                .page("page")
                .addStatus(BetaSpendLimitIncreaseRequestStatus.APPROVED)
                .build()

        val queryParams = params._queryParams()

        assertThat(queryParams)
            .isEqualTo(
                QueryParams.builder()
                    .put("actor_ids[]", "string")
                    .put("limit", "1")
                    .put("page", "page")
                    .put("status[]", "approved")
                    .build()
            )
    }

    @Test
    fun queryParamsWithoutOptionalFields() {
        val params = IncreaseRequestListParams.builder().build()

        val queryParams = params._queryParams()

        assertThat(queryParams).isEqualTo(QueryParams.builder().build())
    }
}
