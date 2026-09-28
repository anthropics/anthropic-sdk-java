package com.anthropic.models.beta.organization.pluginmarketplaces

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaPluginMarketplaceValidationPluginWarningTest {

    @Test
    fun create() {
        val betaPluginMarketplaceValidationPluginWarning =
            BetaPluginMarketplaceValidationPluginWarning.builder()
                .errorCode("marketplace_sync_zipball_symlink_dangling")
                .message("message")
                .build()

        assertThat(betaPluginMarketplaceValidationPluginWarning.errorCode())
            .isEqualTo("marketplace_sync_zipball_symlink_dangling")
        assertThat(betaPluginMarketplaceValidationPluginWarning.message()).isEqualTo("message")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaPluginMarketplaceValidationPluginWarning =
            BetaPluginMarketplaceValidationPluginWarning.builder()
                .errorCode("marketplace_sync_zipball_symlink_dangling")
                .message("message")
                .build()

        val roundtrippedBetaPluginMarketplaceValidationPluginWarning =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaPluginMarketplaceValidationPluginWarning),
                jacksonTypeRef<BetaPluginMarketplaceValidationPluginWarning>(),
            )

        assertThat(roundtrippedBetaPluginMarketplaceValidationPluginWarning)
            .isEqualTo(betaPluginMarketplaceValidationPluginWarning)
    }
}
