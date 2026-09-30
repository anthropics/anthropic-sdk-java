package com.anthropic.models.beta.organization.plugins

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaPluginTargetRbacGroupTest {

    @Test
    fun create() {
        val betaPluginTargetRbacGroup =
            BetaPluginTargetRbacGroup.of("rbac_group_012rppKaSVsmTo6NqRDXQXNF")

        assertThat(betaPluginTargetRbacGroup.rbacGroupId())
            .isEqualTo("rbac_group_012rppKaSVsmTo6NqRDXQXNF")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaPluginTargetRbacGroup =
            BetaPluginTargetRbacGroup.of("rbac_group_012rppKaSVsmTo6NqRDXQXNF")

        val roundtrippedBetaPluginTargetRbacGroup =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaPluginTargetRbacGroup),
                jacksonTypeRef<BetaPluginTargetRbacGroup>(),
            )

        assertThat(roundtrippedBetaPluginTargetRbacGroup).isEqualTo(betaPluginTargetRbacGroup)
    }
}
