package com.anthropic.models.beta.organization.plugins

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaPluginComponentTest {

    @Test
    fun create() {
        val betaPluginComponent =
            BetaPluginComponent.builder()
                .description("description")
                .name("review-pr")
                .type(BetaPluginComponent.Type.SKILL)
                .build()

        assertThat(betaPluginComponent.description()).contains("description")
        assertThat(betaPluginComponent.name()).isEqualTo("review-pr")
        assertThat(betaPluginComponent.type()).isEqualTo(BetaPluginComponent.Type.SKILL)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaPluginComponent =
            BetaPluginComponent.builder()
                .description("description")
                .name("review-pr")
                .type(BetaPluginComponent.Type.SKILL)
                .build()

        val roundtrippedBetaPluginComponent =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaPluginComponent),
                jacksonTypeRef<BetaPluginComponent>(),
            )

        assertThat(roundtrippedBetaPluginComponent).isEqualTo(betaPluginComponent)
    }
}
