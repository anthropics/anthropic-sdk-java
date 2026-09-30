package com.anthropic.models.beta.organization.analytics.artifacts

import com.anthropic.core.http.QueryParams
import java.time.LocalDate
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ArtifactListParamsTest {

    @Test
    fun create() {
        ArtifactListParams.builder()
            .date(LocalDate.parse("2019-12-27"))
            .addFilter("string")
            .addGroupBy(ArtifactListParams.GroupBy.PRODUCT)
            .limit(1L)
            .page("page")
            .build()
    }

    @Test
    fun queryParams() {
        val params =
            ArtifactListParams.builder()
                .date(LocalDate.parse("2019-12-27"))
                .addFilter("string")
                .addGroupBy(ArtifactListParams.GroupBy.PRODUCT)
                .limit(1L)
                .page("page")
                .build()

        val queryParams = params._queryParams()

        assertThat(queryParams)
            .isEqualTo(
                QueryParams.builder()
                    .put("date", "2019-12-27")
                    .put("filter[]", "string")
                    .put("group_by[]", "product")
                    .put("limit", "1")
                    .put("page", "page")
                    .build()
            )
    }

    @Test
    fun queryParamsWithoutOptionalFields() {
        val params = ArtifactListParams.builder().date(LocalDate.parse("2019-12-27")).build()

        val queryParams = params._queryParams()

        assertThat(queryParams).isEqualTo(QueryParams.builder().put("date", "2019-12-27").build())
    }
}
