package com.anthropic.models.beta.organization.plugins

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaPluginNameTakenErrorDetailsTest {

    @Test
    fun create() {
        val betaPluginNameTakenErrorDetails =
            BetaPluginNameTakenErrorDetails.builder().pluginId("plugin_id").build()

        assertThat(betaPluginNameTakenErrorDetails.pluginId()).contains("plugin_id")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaPluginNameTakenErrorDetails =
            BetaPluginNameTakenErrorDetails.builder().pluginId("plugin_id").build()

        val roundtrippedBetaPluginNameTakenErrorDetails =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaPluginNameTakenErrorDetails),
                jacksonTypeRef<BetaPluginNameTakenErrorDetails>(),
            )

        assertThat(roundtrippedBetaPluginNameTakenErrorDetails)
            .isEqualTo(betaPluginNameTakenErrorDetails)
    }
}
