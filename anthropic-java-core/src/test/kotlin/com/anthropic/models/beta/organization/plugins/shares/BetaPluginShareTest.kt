package com.anthropic.models.beta.organization.plugins.shares

import com.anthropic.core.jsonMapper
import com.anthropic.models.beta.organization.plugins.BetaPluginTargetOrganization
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaPluginShareTest {

    @Test
    fun create() {
        val betaPluginShare =
            BetaPluginShare.builder()
                .grantedAt(OffsetDateTime.parse("2026-03-14T09:26:53.589793Z"))
                .pluginId("plugin_01JyHfbRkZvD1gW7oTqXc3Ne")
                .target(BetaPluginTargetOrganization.builder().build())
                .build()

        assertThat(betaPluginShare.grantedAt())
            .isEqualTo(OffsetDateTime.parse("2026-03-14T09:26:53.589793Z"))
        assertThat(betaPluginShare.pluginId()).isEqualTo("plugin_01JyHfbRkZvD1gW7oTqXc3Ne")
        assertThat(betaPluginShare.target())
            .isEqualTo(
                BetaPluginShare.Target.ofOrganization(
                    BetaPluginTargetOrganization.builder().build()
                )
            )
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaPluginShare =
            BetaPluginShare.builder()
                .grantedAt(OffsetDateTime.parse("2026-03-14T09:26:53.589793Z"))
                .pluginId("plugin_01JyHfbRkZvD1gW7oTqXc3Ne")
                .target(BetaPluginTargetOrganization.builder().build())
                .build()

        val roundtrippedBetaPluginShare =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaPluginShare),
                jacksonTypeRef<BetaPluginShare>(),
            )

        assertThat(roundtrippedBetaPluginShare).isEqualTo(betaPluginShare)
    }
}
