package com.anthropic.models.beta.organization.pluginmarketplaces

import com.anthropic.core.jsonMapper
import com.anthropic.models.beta.organization.plugins.BetaPluginOwnerOrganization
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class PluginMarketplaceListPageResponseTest {

    @Test
    fun create() {
        val pluginMarketplaceListPageResponse =
            PluginMarketplaceListPageResponse.builder()
                .addData(
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
                )
                .nextPage("page_MjAyNi0wOS0xNlQxNDowNTowOVo")
                .build()

        assertThat(pluginMarketplaceListPageResponse.data())
            .containsExactly(
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
            )
        assertThat(pluginMarketplaceListPageResponse.nextPage())
            .contains("page_MjAyNi0wOS0xNlQxNDowNTowOVo")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val pluginMarketplaceListPageResponse =
            PluginMarketplaceListPageResponse.builder()
                .addData(
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
                )
                .nextPage("page_MjAyNi0wOS0xNlQxNDowNTowOVo")
                .build()

        val roundtrippedPluginMarketplaceListPageResponse =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(pluginMarketplaceListPageResponse),
                jacksonTypeRef<PluginMarketplaceListPageResponse>(),
            )

        assertThat(roundtrippedPluginMarketplaceListPageResponse)
            .isEqualTo(pluginMarketplaceListPageResponse)
    }
}
