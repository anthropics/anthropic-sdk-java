package com.anthropic.models.organization.federation.rules.workspaces

import com.anthropic.core.http.QueryParams
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class WorkspaceListParamsTest {

    @Test
    fun create() {
        WorkspaceListParams.builder()
            .federationRuleId("federation_rule_id")
            .limit(1L)
            .page("page")
            .build()
    }

    @Test
    fun pathParams() {
        val params = WorkspaceListParams.builder().federationRuleId("federation_rule_id").build()

        assertThat(params._pathParam(0)).isEqualTo("federation_rule_id")
        // out-of-bound path param
        assertThat(params._pathParam(1)).isEqualTo("")
    }

    @Test
    fun queryParams() {
        val params =
            WorkspaceListParams.builder()
                .federationRuleId("federation_rule_id")
                .limit(1L)
                .page("page")
                .build()

        val queryParams = params._queryParams()

        assertThat(queryParams)
            .isEqualTo(QueryParams.builder().put("limit", "1").put("page", "page").build())
    }

    @Test
    fun queryParamsWithoutOptionalFields() {
        val params = WorkspaceListParams.builder().federationRuleId("federation_rule_id").build()

        val queryParams = params._queryParams()

        assertThat(queryParams).isEqualTo(QueryParams.builder().build())
    }
}
