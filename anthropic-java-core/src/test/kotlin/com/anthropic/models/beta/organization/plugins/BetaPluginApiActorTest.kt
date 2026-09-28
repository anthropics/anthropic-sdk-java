package com.anthropic.models.beta.organization.plugins

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaPluginApiActorTest {

    @Test
    fun create() {
        val betaPluginApiActor = BetaPluginApiActor.of("apikey_01Rj2N8SVvo6BePZj99NhmiT")

        assertThat(betaPluginApiActor.apiKeyId()).isEqualTo("apikey_01Rj2N8SVvo6BePZj99NhmiT")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaPluginApiActor = BetaPluginApiActor.of("apikey_01Rj2N8SVvo6BePZj99NhmiT")

        val roundtrippedBetaPluginApiActor =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaPluginApiActor),
                jacksonTypeRef<BetaPluginApiActor>(),
            )

        assertThat(roundtrippedBetaPluginApiActor).isEqualTo(betaPluginApiActor)
    }
}
