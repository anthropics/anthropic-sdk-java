package com.anthropic.models.beta.organization.pluginmarketplaces

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaPluginMarketplaceValidationPluginErrorTest {

    @Test
    fun create() {
        val betaPluginMarketplaceValidationPluginError =
            BetaPluginMarketplaceValidationPluginError.builder()
                .error("error")
                .errorCode("marketplace_sync_plugin_missing_manifest")
                .name("name")
                .build()

        assertThat(betaPluginMarketplaceValidationPluginError.error()).isEqualTo("error")
        assertThat(betaPluginMarketplaceValidationPluginError.errorCode())
            .isEqualTo("marketplace_sync_plugin_missing_manifest")
        assertThat(betaPluginMarketplaceValidationPluginError.name()).isEqualTo("name")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaPluginMarketplaceValidationPluginError =
            BetaPluginMarketplaceValidationPluginError.builder()
                .error("error")
                .errorCode("marketplace_sync_plugin_missing_manifest")
                .name("name")
                .build()

        val roundtrippedBetaPluginMarketplaceValidationPluginError =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaPluginMarketplaceValidationPluginError),
                jacksonTypeRef<BetaPluginMarketplaceValidationPluginError>(),
            )

        assertThat(roundtrippedBetaPluginMarketplaceValidationPluginError)
            .isEqualTo(betaPluginMarketplaceValidationPluginError)
    }
}
