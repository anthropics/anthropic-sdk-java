package com.anthropic.models.organization.federation.rules.workspaces

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class WorkspaceRemoveParamsTest {

    @Test
    fun create() {
        WorkspaceRemoveParams.builder()
            .federationRuleId("federation_rule_id")
            .workspaceId("workspace_id")
            .build()
    }

    @Test
    fun pathParams() {
        val params =
            WorkspaceRemoveParams.builder()
                .federationRuleId("federation_rule_id")
                .workspaceId("workspace_id")
                .build()

        assertThat(params._pathParam(0)).isEqualTo("federation_rule_id")
        assertThat(params._pathParam(1)).isEqualTo("workspace_id")
        // out-of-bound path param
        assertThat(params._pathParam(2)).isEqualTo("")
    }
}
