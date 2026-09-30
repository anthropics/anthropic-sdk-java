package com.anthropic.models.beta.organization.analytics.summaries

import com.anthropic.core.http.QueryParams
import java.time.LocalDate
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class SummaryListParamsTest {

    @Test
    fun create() {
        SummaryListParams.builder()
            .startingDate(LocalDate.parse("2019-12-27"))
            .endingDate(LocalDate.parse("2019-12-27"))
            .addFilter("string")
            .limit(1L)
            .page("page")
            .build()
    }

    @Test
    fun queryParams() {
        val params =
            SummaryListParams.builder()
                .startingDate(LocalDate.parse("2019-12-27"))
                .endingDate(LocalDate.parse("2019-12-27"))
                .addFilter("string")
                .limit(1L)
                .page("page")
                .build()

        val queryParams = params._queryParams()

        assertThat(queryParams)
            .isEqualTo(
                QueryParams.builder()
                    .put("starting_date", "2019-12-27")
                    .put("ending_date", "2019-12-27")
                    .put("filter[]", "string")
                    .put("limit", "1")
                    .put("page", "page")
                    .build()
            )
    }

    @Test
    fun queryParamsWithoutOptionalFields() {
        val params = SummaryListParams.builder().startingDate(LocalDate.parse("2019-12-27")).build()

        val queryParams = params._queryParams()

        assertThat(queryParams)
            .isEqualTo(QueryParams.builder().put("starting_date", "2019-12-27").build())
    }
}
