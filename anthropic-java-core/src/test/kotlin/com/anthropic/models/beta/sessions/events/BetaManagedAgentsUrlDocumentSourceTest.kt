package com.anthropic.models.beta.sessions.events

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsUrlDocumentSourceTest {

    @Test
    fun create() {
        val betaManagedAgentsUrlDocumentSource = BetaManagedAgentsUrlDocumentSource.of("x")

        assertThat(betaManagedAgentsUrlDocumentSource.url()).isEqualTo("x")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsUrlDocumentSource = BetaManagedAgentsUrlDocumentSource.of("x")

        val roundtrippedBetaManagedAgentsUrlDocumentSource =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsUrlDocumentSource),
                jacksonTypeRef<BetaManagedAgentsUrlDocumentSource>(),
            )

        assertThat(roundtrippedBetaManagedAgentsUrlDocumentSource)
            .isEqualTo(betaManagedAgentsUrlDocumentSource)
    }
}
