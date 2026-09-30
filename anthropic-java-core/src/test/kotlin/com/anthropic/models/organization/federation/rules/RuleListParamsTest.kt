package com.anthropic.models.organization.federation.rules

import com.anthropic.core.http.QueryParams
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class RuleListParamsTest {

    @Test
    fun create() {
        RuleListParams.builder()
            .includeArchived(true)
            .issuerId("issuer_id")
            .limit(1L)
            .page("page")
            .build()
    }

    @Test
    fun queryParams() {
        val params =
            RuleListParams.builder()
                .includeArchived(true)
                .issuerId("issuer_id")
                .limit(1L)
                .page("page")
                .build()

        val queryParams = params._queryParams()

        assertThat(queryParams)
            .isEqualTo(
                QueryParams.builder()
                    .put("include_archived", "true")
                    .put("issuer_id", "issuer_id")
                    .put("limit", "1")
                    .put("page", "page")
                    .build()
            )
    }

    @Test
    fun queryParamsWithoutOptionalFields() {
        val params = RuleListParams.builder().build()

        val queryParams = params._queryParams()

        assertThat(queryParams).isEqualTo(QueryParams.builder().build())
    }
}
