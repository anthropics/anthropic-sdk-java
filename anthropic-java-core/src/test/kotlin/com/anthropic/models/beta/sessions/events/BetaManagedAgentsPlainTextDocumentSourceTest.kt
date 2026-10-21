package com.anthropic.models.beta.sessions.events

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsPlainTextDocumentSourceTest {

    @Test
    fun create() {
        val betaManagedAgentsPlainTextDocumentSource =
            BetaManagedAgentsPlainTextDocumentSource.of("x")

        assertThat(betaManagedAgentsPlainTextDocumentSource.data()).isEqualTo("x")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsPlainTextDocumentSource =
            BetaManagedAgentsPlainTextDocumentSource.of("x")

        val roundtrippedBetaManagedAgentsPlainTextDocumentSource =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsPlainTextDocumentSource),
                jacksonTypeRef<BetaManagedAgentsPlainTextDocumentSource>(),
            )

        assertThat(roundtrippedBetaManagedAgentsPlainTextDocumentSource)
            .isEqualTo(betaManagedAgentsPlainTextDocumentSource)
    }
}
