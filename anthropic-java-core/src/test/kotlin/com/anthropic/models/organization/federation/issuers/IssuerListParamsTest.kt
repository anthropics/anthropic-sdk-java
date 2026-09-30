package com.anthropic.models.organization.federation.issuers

import com.anthropic.core.http.QueryParams
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class IssuerListParamsTest {

    @Test
    fun create() {
        IssuerListParams.builder().includeArchived(true).limit(1L).page("page").build()
    }

    @Test
    fun queryParams() {
        val params = IssuerListParams.builder().includeArchived(true).limit(1L).page("page").build()

        val queryParams = params._queryParams()

        assertThat(queryParams)
            .isEqualTo(
                QueryParams.builder()
                    .put("include_archived", "true")
                    .put("limit", "1")
                    .put("page", "page")
                    .build()
            )
    }

    @Test
    fun queryParamsWithoutOptionalFields() {
        val params = IssuerListParams.builder().build()

        val queryParams = params._queryParams()

        assertThat(queryParams).isEqualTo(QueryParams.builder().build())
    }
}
