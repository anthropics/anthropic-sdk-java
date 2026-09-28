package com.anthropic.models.beta.organization.plugins

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaPluginOwnerOrganizationTest {

    @Test
    fun create() {
        val betaPluginOwnerOrganization = BetaPluginOwnerOrganization.builder().build()
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaPluginOwnerOrganization = BetaPluginOwnerOrganization.builder().build()

        val roundtrippedBetaPluginOwnerOrganization =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaPluginOwnerOrganization),
                jacksonTypeRef<BetaPluginOwnerOrganization>(),
            )

        assertThat(roundtrippedBetaPluginOwnerOrganization).isEqualTo(betaPluginOwnerOrganization)
    }
}
