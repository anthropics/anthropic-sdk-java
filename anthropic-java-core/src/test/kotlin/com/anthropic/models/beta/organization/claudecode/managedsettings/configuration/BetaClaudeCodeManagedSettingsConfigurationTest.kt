package com.anthropic.models.beta.organization.claudecode.managedsettings.configuration

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaClaudeCodeManagedSettingsConfigurationTest {

    @Test
    fun create() {
        val betaClaudeCodeManagedSettingsConfiguration =
            BetaClaudeCodeManagedSettingsConfiguration.builder()
                .mode(BetaClaudeCodeManagedSettingsOrganizationMode.builder().build())
                .modeUpdatedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .addRbacGroupOrder("rbac_group_012rppKaSVsmTo6NqRDXQXNF")
                .rbacGroupOrderVersion("rbac_group_order_version")
                .build()

        assertThat(betaClaudeCodeManagedSettingsConfiguration.mode())
            .isEqualTo(
                BetaClaudeCodeManagedSettingsMode.ofOrganization(
                    BetaClaudeCodeManagedSettingsOrganizationMode.builder().build()
                )
            )
        assertThat(betaClaudeCodeManagedSettingsConfiguration.modeUpdatedAt())
            .contains(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
        assertThat(betaClaudeCodeManagedSettingsConfiguration.rbacGroupOrder())
            .containsExactly("rbac_group_012rppKaSVsmTo6NqRDXQXNF")
        assertThat(betaClaudeCodeManagedSettingsConfiguration.rbacGroupOrderVersion())
            .isEqualTo("rbac_group_order_version")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaClaudeCodeManagedSettingsConfiguration =
            BetaClaudeCodeManagedSettingsConfiguration.builder()
                .mode(BetaClaudeCodeManagedSettingsOrganizationMode.builder().build())
                .modeUpdatedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .addRbacGroupOrder("rbac_group_012rppKaSVsmTo6NqRDXQXNF")
                .rbacGroupOrderVersion("rbac_group_order_version")
                .build()

        val roundtrippedBetaClaudeCodeManagedSettingsConfiguration =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaClaudeCodeManagedSettingsConfiguration),
                jacksonTypeRef<BetaClaudeCodeManagedSettingsConfiguration>(),
            )

        assertThat(roundtrippedBetaClaudeCodeManagedSettingsConfiguration)
            .isEqualTo(betaClaudeCodeManagedSettingsConfiguration)
    }
}
