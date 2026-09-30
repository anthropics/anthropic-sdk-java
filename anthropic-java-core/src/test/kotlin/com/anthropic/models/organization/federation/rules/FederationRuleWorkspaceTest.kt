package com.anthropic.models.organization.federation.rules

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class FederationRuleWorkspaceTest {

    @Test
    fun create() {
        val federationRuleWorkspace =
            FederationRuleWorkspace.builder()
                .createdAt(OffsetDateTime.parse("2024-10-30T23:58:27.427722Z"))
                .createdByActorId("created_by_actor_id")
                .federationRuleId("federation_rule_id")
                .workspaceId("workspace_id")
                .workspaceName("workspace_name")
                .build()

        assertThat(federationRuleWorkspace.createdAt())
            .isEqualTo(OffsetDateTime.parse("2024-10-30T23:58:27.427722Z"))
        assertThat(federationRuleWorkspace.createdByActorId()).contains("created_by_actor_id")
        assertThat(federationRuleWorkspace.federationRuleId()).isEqualTo("federation_rule_id")
        assertThat(federationRuleWorkspace.workspaceId()).isEqualTo("workspace_id")
        assertThat(federationRuleWorkspace.workspaceName()).contains("workspace_name")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val federationRuleWorkspace =
            FederationRuleWorkspace.builder()
                .createdAt(OffsetDateTime.parse("2024-10-30T23:58:27.427722Z"))
                .createdByActorId("created_by_actor_id")
                .federationRuleId("federation_rule_id")
                .workspaceId("workspace_id")
                .workspaceName("workspace_name")
                .build()

        val roundtrippedFederationRuleWorkspace =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(federationRuleWorkspace),
                jacksonTypeRef<FederationRuleWorkspace>(),
            )

        assertThat(roundtrippedFederationRuleWorkspace).isEqualTo(federationRuleWorkspace)
    }
}
