package com.anthropic.models.beta.organization.claudecode.managedsettings.rbacgroups

import com.anthropic.core.JsonValue
import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaClaudeCodeRbacGroupManagedSettingsTest {

    @Test
    fun create() {
        val betaClaudeCodeRbacGroupManagedSettings =
            BetaClaudeCodeRbacGroupManagedSettings.builder()
                .createdAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .rbacGroupId("rbac_group_012rppKaSVsmTo6NqRDXQXNF")
                .rbacGroupStatus(BetaClaudeCodeRbacGroupStatusActive.builder().build())
                .settings(
                    BetaClaudeCodeRbacGroupManagedSettings.Settings.builder()
                        .putAdditionalProperty("foo", JsonValue.from("bar"))
                        .build()
                )
                .updatedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .build()

        assertThat(betaClaudeCodeRbacGroupManagedSettings.createdAt())
            .isEqualTo(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
        assertThat(betaClaudeCodeRbacGroupManagedSettings.rbacGroupId())
            .isEqualTo("rbac_group_012rppKaSVsmTo6NqRDXQXNF")
        assertThat(betaClaudeCodeRbacGroupManagedSettings.rbacGroupStatus())
            .isEqualTo(
                BetaClaudeCodeRbacGroupStatus.ofActive(
                    BetaClaudeCodeRbacGroupStatusActive.builder().build()
                )
            )
        assertThat(betaClaudeCodeRbacGroupManagedSettings.settings())
            .contains(
                BetaClaudeCodeRbacGroupManagedSettings.Settings.builder()
                    .putAdditionalProperty("foo", JsonValue.from("bar"))
                    .build()
            )
        assertThat(betaClaudeCodeRbacGroupManagedSettings.updatedAt())
            .isEqualTo(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaClaudeCodeRbacGroupManagedSettings =
            BetaClaudeCodeRbacGroupManagedSettings.builder()
                .createdAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .rbacGroupId("rbac_group_012rppKaSVsmTo6NqRDXQXNF")
                .rbacGroupStatus(BetaClaudeCodeRbacGroupStatusActive.builder().build())
                .settings(
                    BetaClaudeCodeRbacGroupManagedSettings.Settings.builder()
                        .putAdditionalProperty("foo", JsonValue.from("bar"))
                        .build()
                )
                .updatedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .build()

        val roundtrippedBetaClaudeCodeRbacGroupManagedSettings =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaClaudeCodeRbacGroupManagedSettings),
                jacksonTypeRef<BetaClaudeCodeRbacGroupManagedSettings>(),
            )

        assertThat(roundtrippedBetaClaudeCodeRbacGroupManagedSettings)
            .isEqualTo(betaClaudeCodeRbacGroupManagedSettings)
    }
}
