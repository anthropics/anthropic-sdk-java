package com.anthropic.models.beta.organization.pluginmarketplaces

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaPluginMarketplaceValidationReportTest {

    @Test
    fun create() {
        val betaPluginMarketplaceValidationReport =
            BetaPluginMarketplaceValidationReport.builder()
                .commitSha("9fceb02d0ae598e95dc970b74767f19372d61af8")
                .manifestError("manifest_error")
                .manifestErrorCode("marketplace_sync_manifest_not_found")
                .addPluginError(
                    BetaPluginMarketplaceValidationPluginError.builder()
                        .error("error")
                        .errorCode("marketplace_sync_plugin_missing_manifest")
                        .name("name")
                        .build()
                )
                .addPluginWarning(
                    BetaPluginMarketplaceValidationPluginWarnings.builder()
                        .name("name")
                        .addWarning(
                            BetaPluginMarketplaceValidationPluginWarning.builder()
                                .errorCode("marketplace_sync_zipball_symlink_dangling")
                                .message("message")
                                .build()
                        )
                        .build()
                )
                .ref("main")
                .totalPluginCount(0L)
                .valid(false)
                .build()

        assertThat(betaPluginMarketplaceValidationReport.commitSha())
            .contains("9fceb02d0ae598e95dc970b74767f19372d61af8")
        assertThat(betaPluginMarketplaceValidationReport.manifestError()).contains("manifest_error")
        assertThat(betaPluginMarketplaceValidationReport.manifestErrorCode())
            .contains("marketplace_sync_manifest_not_found")
        assertThat(betaPluginMarketplaceValidationReport.pluginErrors())
            .containsExactly(
                BetaPluginMarketplaceValidationPluginError.builder()
                    .error("error")
                    .errorCode("marketplace_sync_plugin_missing_manifest")
                    .name("name")
                    .build()
            )
        assertThat(betaPluginMarketplaceValidationReport.pluginWarnings())
            .containsExactly(
                BetaPluginMarketplaceValidationPluginWarnings.builder()
                    .name("name")
                    .addWarning(
                        BetaPluginMarketplaceValidationPluginWarning.builder()
                            .errorCode("marketplace_sync_zipball_symlink_dangling")
                            .message("message")
                            .build()
                    )
                    .build()
            )
        assertThat(betaPluginMarketplaceValidationReport.ref()).contains("main")
        assertThat(betaPluginMarketplaceValidationReport.totalPluginCount()).isEqualTo(0L)
        assertThat(betaPluginMarketplaceValidationReport.valid()).isEqualTo(false)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaPluginMarketplaceValidationReport =
            BetaPluginMarketplaceValidationReport.builder()
                .commitSha("9fceb02d0ae598e95dc970b74767f19372d61af8")
                .manifestError("manifest_error")
                .manifestErrorCode("marketplace_sync_manifest_not_found")
                .addPluginError(
                    BetaPluginMarketplaceValidationPluginError.builder()
                        .error("error")
                        .errorCode("marketplace_sync_plugin_missing_manifest")
                        .name("name")
                        .build()
                )
                .addPluginWarning(
                    BetaPluginMarketplaceValidationPluginWarnings.builder()
                        .name("name")
                        .addWarning(
                            BetaPluginMarketplaceValidationPluginWarning.builder()
                                .errorCode("marketplace_sync_zipball_symlink_dangling")
                                .message("message")
                                .build()
                        )
                        .build()
                )
                .ref("main")
                .totalPluginCount(0L)
                .valid(false)
                .build()

        val roundtrippedBetaPluginMarketplaceValidationReport =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaPluginMarketplaceValidationReport),
                jacksonTypeRef<BetaPluginMarketplaceValidationReport>(),
            )

        assertThat(roundtrippedBetaPluginMarketplaceValidationReport)
            .isEqualTo(betaPluginMarketplaceValidationReport)
    }
}
