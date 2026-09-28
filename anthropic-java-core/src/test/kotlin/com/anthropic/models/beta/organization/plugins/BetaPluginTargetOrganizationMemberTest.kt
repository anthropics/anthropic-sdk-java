package com.anthropic.models.beta.organization.plugins

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaPluginTargetOrganizationMemberTest {

    @Test
    fun create() {
        val betaPluginTargetOrganizationMember =
            BetaPluginTargetOrganizationMember.of("user_01WCz1FkmYMm4gnmykNKUu3Q")

        assertThat(betaPluginTargetOrganizationMember.userId())
            .isEqualTo("user_01WCz1FkmYMm4gnmykNKUu3Q")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaPluginTargetOrganizationMember =
            BetaPluginTargetOrganizationMember.of("user_01WCz1FkmYMm4gnmykNKUu3Q")

        val roundtrippedBetaPluginTargetOrganizationMember =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaPluginTargetOrganizationMember),
                jacksonTypeRef<BetaPluginTargetOrganizationMember>(),
            )

        assertThat(roundtrippedBetaPluginTargetOrganizationMember)
            .isEqualTo(betaPluginTargetOrganizationMember)
    }
}
