package com.anthropic.models.organization.federation.rules.workspaces

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class WorkspaceAddParamsTest {

    @Test
    fun create() {
        WorkspaceAddParams.builder()
            .federationRuleId("federation_rule_id")
            .workspaceId("wrkspc_01JwQvzr7rXLA5AGx3HKfFUJ")
            .build()
    }

    @Test
    fun pathParams() {
        val params =
            WorkspaceAddParams.builder()
                .federationRuleId("federation_rule_id")
                .workspaceId("wrkspc_01JwQvzr7rXLA5AGx3HKfFUJ")
                .build()

        assertThat(params._pathParam(0)).isEqualTo("federation_rule_id")
        // out-of-bound path param
        assertThat(params._pathParam(1)).isEqualTo("")
    }

    @Test
    fun body() {
        val params =
            WorkspaceAddParams.builder()
                .federationRuleId("federation_rule_id")
                .workspaceId("wrkspc_01JwQvzr7rXLA5AGx3HKfFUJ")
                .build()

        val body = params._body()

        assertThat(body.workspaceId()).isEqualTo("wrkspc_01JwQvzr7rXLA5AGx3HKfFUJ")
    }
}
