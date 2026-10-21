package com.anthropic.models.beta.sessions.events

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsFileDocumentSourceTest {

    @Test
    fun create() {
        val betaManagedAgentsFileDocumentSource = BetaManagedAgentsFileDocumentSource.of("x")

        assertThat(betaManagedAgentsFileDocumentSource.fileId()).isEqualTo("x")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsFileDocumentSource = BetaManagedAgentsFileDocumentSource.of("x")

        val roundtrippedBetaManagedAgentsFileDocumentSource =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsFileDocumentSource),
                jacksonTypeRef<BetaManagedAgentsFileDocumentSource>(),
            )

        assertThat(roundtrippedBetaManagedAgentsFileDocumentSource)
            .isEqualTo(betaManagedAgentsFileDocumentSource)
    }
}
