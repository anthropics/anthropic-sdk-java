// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.organization.federation.rules

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaFederationRuleWorkspaceTest {

    @Test
    fun create() {
        val betaFederationRuleWorkspace =
            BetaFederationRuleWorkspace.builder()
                .createdAt(OffsetDateTime.parse("2024-10-30T23:58:27.427722Z"))
                .createdByActorId("created_by_actor_id")
                .federationRuleId("federation_rule_id")
                .workspaceId("workspace_id")
                .workspaceName("workspace_name")
                .build()

        assertThat(betaFederationRuleWorkspace.createdAt())
            .isEqualTo(OffsetDateTime.parse("2024-10-30T23:58:27.427722Z"))
        assertThat(betaFederationRuleWorkspace.createdByActorId()).contains("created_by_actor_id")
        assertThat(betaFederationRuleWorkspace.federationRuleId()).isEqualTo("federation_rule_id")
        assertThat(betaFederationRuleWorkspace.workspaceId()).isEqualTo("workspace_id")
        assertThat(betaFederationRuleWorkspace.workspaceName()).contains("workspace_name")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaFederationRuleWorkspace =
            BetaFederationRuleWorkspace.builder()
                .createdAt(OffsetDateTime.parse("2024-10-30T23:58:27.427722Z"))
                .createdByActorId("created_by_actor_id")
                .federationRuleId("federation_rule_id")
                .workspaceId("workspace_id")
                .workspaceName("workspace_name")
                .build()

        val roundtrippedBetaFederationRuleWorkspace =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaFederationRuleWorkspace),
                jacksonTypeRef<BetaFederationRuleWorkspace>(),
            )

        assertThat(roundtrippedBetaFederationRuleWorkspace).isEqualTo(betaFederationRuleWorkspace)
    }
}
