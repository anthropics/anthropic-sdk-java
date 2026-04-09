// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.sessions.events

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsUrlDocumentSourceTest {

    @Test
    fun create() {
        val betaManagedAgentsUrlDocumentSource =
            BetaManagedAgentsUrlDocumentSource.builder()
                .type(BetaManagedAgentsUrlDocumentSource.Type.URL)
                .url("x")
                .build()

        assertThat(betaManagedAgentsUrlDocumentSource.type())
            .isEqualTo(BetaManagedAgentsUrlDocumentSource.Type.URL)
        assertThat(betaManagedAgentsUrlDocumentSource.url()).isEqualTo("x")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsUrlDocumentSource =
            BetaManagedAgentsUrlDocumentSource.builder()
                .type(BetaManagedAgentsUrlDocumentSource.Type.URL)
                .url("x")
                .build()

        val roundtrippedBetaManagedAgentsUrlDocumentSource =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsUrlDocumentSource),
                jacksonTypeRef<BetaManagedAgentsUrlDocumentSource>(),
            )

        assertThat(roundtrippedBetaManagedAgentsUrlDocumentSource)
            .isEqualTo(betaManagedAgentsUrlDocumentSource)
    }
}
