package com.anthropic.models.beta.organization.pluginmarketplaces

import com.anthropic.core.jsonMapper
import com.anthropic.models.beta.organization.plugins.BetaPluginOwnerOrganization
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaPluginMarketplaceTest {

    @Test
    fun create() {
        val betaPluginMarketplace =
            BetaPluginMarketplace.builder()
                .id("marketplace_01HxQ3v9KpZ2mTn8RwLc4Ys7")
                .createdAt(OffsetDateTime.parse("2026-03-14T09:26:53.589793Z"))
                .defaultInstallationPreference(
                    BetaPluginMarketplace.DefaultInstallationPreference.AVAILABLE
                )
                .lastSyncEndedAt(OffsetDateTime.parse("2026-03-14T09:26:53.589793Z"))
                .lastSyncReadSha("9fceb02d0ae598e95dc970b74767f19372d61af8")
                .name("engineering-tools")
                .owner(BetaPluginOwnerOrganization.builder().build())
                .source(BetaPluginMarketplace.Source.GITHUB)
                .syncStatus(BetaPluginMarketplace.SyncStatus.SUCCESS)
                .build()

        assertThat(betaPluginMarketplace.id()).isEqualTo("marketplace_01HxQ3v9KpZ2mTn8RwLc4Ys7")
        assertThat(betaPluginMarketplace.createdAt())
            .isEqualTo(OffsetDateTime.parse("2026-03-14T09:26:53.589793Z"))
        assertThat(betaPluginMarketplace.defaultInstallationPreference())
            .contains(BetaPluginMarketplace.DefaultInstallationPreference.AVAILABLE)
        assertThat(betaPluginMarketplace.lastSyncEndedAt())
            .contains(OffsetDateTime.parse("2026-03-14T09:26:53.589793Z"))
        assertThat(betaPluginMarketplace.lastSyncReadSha())
            .contains("9fceb02d0ae598e95dc970b74767f19372d61af8")
        assertThat(betaPluginMarketplace.name()).isEqualTo("engineering-tools")
        assertThat(betaPluginMarketplace.owner())
            .isEqualTo(
                BetaPluginMarketplace.Owner.ofOrganization(
                    BetaPluginOwnerOrganization.builder().build()
                )
            )
        assertThat(betaPluginMarketplace.source()).isEqualTo(BetaPluginMarketplace.Source.GITHUB)
        assertThat(betaPluginMarketplace.syncStatus())
            .contains(BetaPluginMarketplace.SyncStatus.SUCCESS)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaPluginMarketplace =
            BetaPluginMarketplace.builder()
                .id("marketplace_01HxQ3v9KpZ2mTn8RwLc4Ys7")
                .createdAt(OffsetDateTime.parse("2026-03-14T09:26:53.589793Z"))
                .defaultInstallationPreference(
                    BetaPluginMarketplace.DefaultInstallationPreference.AVAILABLE
                )
                .lastSyncEndedAt(OffsetDateTime.parse("2026-03-14T09:26:53.589793Z"))
                .lastSyncReadSha("9fceb02d0ae598e95dc970b74767f19372d61af8")
                .name("engineering-tools")
                .owner(BetaPluginOwnerOrganization.builder().build())
                .source(BetaPluginMarketplace.Source.GITHUB)
                .syncStatus(BetaPluginMarketplace.SyncStatus.SUCCESS)
                .build()

        val roundtrippedBetaPluginMarketplace =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaPluginMarketplace),
                jacksonTypeRef<BetaPluginMarketplace>(),
            )

        assertThat(roundtrippedBetaPluginMarketplace).isEqualTo(betaPluginMarketplace)
    }
}
