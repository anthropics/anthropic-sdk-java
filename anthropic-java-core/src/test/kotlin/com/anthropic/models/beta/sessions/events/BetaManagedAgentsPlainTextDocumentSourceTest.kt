// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.sessions.events

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsPlainTextDocumentSourceTest {

    @Test
    fun create() {
        val betaManagedAgentsPlainTextDocumentSource =
            BetaManagedAgentsPlainTextDocumentSource.builder()
                .data("x")
                .mediaType(BetaManagedAgentsPlainTextDocumentSource.MediaType.TEXT_PLAIN)
                .type(BetaManagedAgentsPlainTextDocumentSource.Type.TEXT)
                .build()

        assertThat(betaManagedAgentsPlainTextDocumentSource.data()).isEqualTo("x")
        assertThat(betaManagedAgentsPlainTextDocumentSource.mediaType())
            .isEqualTo(BetaManagedAgentsPlainTextDocumentSource.MediaType.TEXT_PLAIN)
        assertThat(betaManagedAgentsPlainTextDocumentSource.type())
            .isEqualTo(BetaManagedAgentsPlainTextDocumentSource.Type.TEXT)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsPlainTextDocumentSource =
            BetaManagedAgentsPlainTextDocumentSource.builder()
                .data("x")
                .mediaType(BetaManagedAgentsPlainTextDocumentSource.MediaType.TEXT_PLAIN)
                .type(BetaManagedAgentsPlainTextDocumentSource.Type.TEXT)
                .build()

        val roundtrippedBetaManagedAgentsPlainTextDocumentSource =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsPlainTextDocumentSource),
                jacksonTypeRef<BetaManagedAgentsPlainTextDocumentSource>(),
            )

        assertThat(roundtrippedBetaManagedAgentsPlainTextDocumentSource)
            .isEqualTo(betaManagedAgentsPlainTextDocumentSource)
    }
}
