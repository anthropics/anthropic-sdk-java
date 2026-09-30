package com.anthropic.models.beta.organization.analytics.skills

import com.anthropic.core.http.QueryParams
import java.time.LocalDate
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class SkillListParamsTest {

    @Test
    fun create() {
        SkillListParams.builder()
            .date(LocalDate.parse("2019-12-27"))
            .endingDate(LocalDate.parse("2019-12-27"))
            .addFilter("string")
            .addGroupBy(SkillListParams.GroupBy.PRODUCT)
            .limit(1L)
            .order(SkillListParams.Order.ASC)
            .orderBy("order_by")
            .page("page")
            .startingDate(LocalDate.parse("2019-12-27"))
            .build()
    }

    @Test
    fun queryParams() {
        val params =
            SkillListParams.builder()
                .date(LocalDate.parse("2019-12-27"))
                .endingDate(LocalDate.parse("2019-12-27"))
                .addFilter("string")
                .addGroupBy(SkillListParams.GroupBy.PRODUCT)
                .limit(1L)
                .order(SkillListParams.Order.ASC)
                .orderBy("order_by")
                .page("page")
                .startingDate(LocalDate.parse("2019-12-27"))
                .build()

        val queryParams = params._queryParams()

        assertThat(queryParams)
            .isEqualTo(
                QueryParams.builder()
                    .put("date", "2019-12-27")
                    .put("ending_date", "2019-12-27")
                    .put("filter[]", "string")
                    .put("group_by[]", "product")
                    .put("limit", "1")
                    .put("order", "asc")
                    .put("order_by", "order_by")
                    .put("page", "page")
                    .put("starting_date", "2019-12-27")
                    .build()
            )
    }

    @Test
    fun queryParamsWithoutOptionalFields() {
        val params = SkillListParams.builder().build()

        val queryParams = params._queryParams()

        assertThat(queryParams).isEqualTo(QueryParams.builder().build())
    }
}
