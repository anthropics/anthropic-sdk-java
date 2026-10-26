package com.anthropic.models.beta.organization.plugins

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaPluginUserActorTest {

    @Test
    fun create() {
        val betaPluginUserActor =
            BetaPluginUserActor.builder()
                .emailAddress("user@example.com")
                .userId("user_01WxYzAbCdEfGhJkMnPqRsTu")
                .build()

        assertThat(betaPluginUserActor.emailAddress()).contains("user@example.com")
        assertThat(betaPluginUserActor.userId()).isEqualTo("user_01WxYzAbCdEfGhJkMnPqRsTu")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaPluginUserActor =
            BetaPluginUserActor.builder()
                .emailAddress("user@example.com")
                .userId("user_01WxYzAbCdEfGhJkMnPqRsTu")
                .build()

        val roundtrippedBetaPluginUserActor =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaPluginUserActor),
                jacksonTypeRef<BetaPluginUserActor>(),
            )

        assertThat(roundtrippedBetaPluginUserActor).isEqualTo(betaPluginUserActor)
    }
}
