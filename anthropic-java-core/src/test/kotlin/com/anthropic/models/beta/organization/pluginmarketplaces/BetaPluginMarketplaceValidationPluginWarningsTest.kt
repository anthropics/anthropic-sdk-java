package com.anthropic.models.beta.organization.pluginmarketplaces

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaPluginMarketplaceValidationPluginWarningsTest {

    @Test
    fun create() {
        val betaPluginMarketplaceValidationPluginWarnings =
            BetaPluginMarketplaceValidationPluginWarnings.builder()
                .name("name")
                .addWarning(
                    BetaPluginMarketplaceValidationPluginWarning.builder()
                        .errorCode("marketplace_sync_zipball_symlink_dangling")
                        .message("message")
                        .build()
                )
                .build()

        assertThat(betaPluginMarketplaceValidationPluginWarnings.name()).isEqualTo("name")
        assertThat(betaPluginMarketplaceValidationPluginWarnings.warnings())
            .containsExactly(
                BetaPluginMarketplaceValidationPluginWarning.builder()
                    .errorCode("marketplace_sync_zipball_symlink_dangling")
                    .message("message")
                    .build()
            )
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaPluginMarketplaceValidationPluginWarnings =
            BetaPluginMarketplaceValidationPluginWarnings.builder()
                .name("name")
                .addWarning(
                    BetaPluginMarketplaceValidationPluginWarning.builder()
                        .errorCode("marketplace_sync_zipball_symlink_dangling")
                        .message("message")
                        .build()
                )
                .build()

        val roundtrippedBetaPluginMarketplaceValidationPluginWarnings =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaPluginMarketplaceValidationPluginWarnings),
                jacksonTypeRef<BetaPluginMarketplaceValidationPluginWarnings>(),
            )

        assertThat(roundtrippedBetaPluginMarketplaceValidationPluginWarnings)
            .isEqualTo(betaPluginMarketplaceValidationPluginWarnings)
    }
}
