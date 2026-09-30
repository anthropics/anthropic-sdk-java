package com.anthropic.models.organization.federation.rules.workspaces

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class WorkspaceAddParamsTest {

    @Test
    fun create() {
        WorkspaceAddParams.builder()
            .federationRuleId("federation_rule_id")
            .workspaceId("workspace_id")
            .build()
    }

    @Test
    fun pathParams() {
        val params =
            WorkspaceAddParams.builder()
                .federationRuleId("federation_rule_id")
                .workspaceId("workspace_id")
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
                .workspaceId("workspace_id")
                .build()

        val body = params._body()

        assertThat(body.workspaceId()).isEqualTo("workspace_id")
    }
}
