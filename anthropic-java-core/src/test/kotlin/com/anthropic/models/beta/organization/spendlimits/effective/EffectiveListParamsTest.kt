package com.anthropic.models.beta.organization.spendlimits.effective

import com.anthropic.core.http.QueryParams
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class EffectiveListParamsTest {

    @Test
    fun create() {
        EffectiveListParams.builder()
            .limit(1L)
            .page("page")
            .addPeriod(EffectiveListParams.Period.DAILY)
            .addUserId("string")
            .build()
    }

    @Test
    fun queryParams() {
        val params =
            EffectiveListParams.builder()
                .limit(1L)
                .page("page")
                .addPeriod(EffectiveListParams.Period.DAILY)
                .addUserId("string")
                .build()

        val queryParams = params._queryParams()

        assertThat(queryParams)
            .isEqualTo(
                QueryParams.builder()
                    .put("limit", "1")
                    .put("page", "page")
                    .put("period[]", "daily")
                    .put("user_ids[]", "string")
                    .build()
            )
    }

    @Test
    fun queryParamsWithoutOptionalFields() {
        val params = EffectiveListParams.builder().build()

        val queryParams = params._queryParams()

        assertThat(queryParams).isEqualTo(QueryParams.builder().build())
    }
}
