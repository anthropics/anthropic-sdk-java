package com.anthropic.models.beta.organization.plugins

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaDeletedPluginTest {

    @Test
    fun create() {
        val betaDeletedPlugin = BetaDeletedPlugin.of("plugin_01JyHfbRkZvD1gW7oTqXc3Ne")

        assertThat(betaDeletedPlugin.id()).isEqualTo("plugin_01JyHfbRkZvD1gW7oTqXc3Ne")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaDeletedPlugin = BetaDeletedPlugin.of("plugin_01JyHfbRkZvD1gW7oTqXc3Ne")

        val roundtrippedBetaDeletedPlugin =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaDeletedPlugin),
                jacksonTypeRef<BetaDeletedPlugin>(),
            )

        assertThat(roundtrippedBetaDeletedPlugin).isEqualTo(betaDeletedPlugin)
    }
}
