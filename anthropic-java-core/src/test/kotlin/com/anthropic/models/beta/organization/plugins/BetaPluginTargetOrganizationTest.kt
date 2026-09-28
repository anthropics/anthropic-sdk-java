package com.anthropic.models.beta.organization.plugins

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaPluginTargetOrganizationTest {

    @Test
    fun create() {
        val betaPluginTargetOrganization = BetaPluginTargetOrganization.builder().build()
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaPluginTargetOrganization = BetaPluginTargetOrganization.builder().build()

        val roundtrippedBetaPluginTargetOrganization =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaPluginTargetOrganization),
                jacksonTypeRef<BetaPluginTargetOrganization>(),
            )

        assertThat(roundtrippedBetaPluginTargetOrganization).isEqualTo(betaPluginTargetOrganization)
    }
}
