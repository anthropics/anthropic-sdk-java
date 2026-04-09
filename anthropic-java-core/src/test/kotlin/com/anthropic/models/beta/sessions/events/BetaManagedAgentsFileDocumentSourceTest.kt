// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.sessions.events

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsFileDocumentSourceTest {

    @Test
    fun create() {
        val betaManagedAgentsFileDocumentSource =
            BetaManagedAgentsFileDocumentSource.builder()
                .fileId("x")
                .type(BetaManagedAgentsFileDocumentSource.Type.FILE)
                .build()

        assertThat(betaManagedAgentsFileDocumentSource.fileId()).isEqualTo("x")
        assertThat(betaManagedAgentsFileDocumentSource.type())
            .isEqualTo(BetaManagedAgentsFileDocumentSource.Type.FILE)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsFileDocumentSource =
            BetaManagedAgentsFileDocumentSource.builder()
                .fileId("x")
                .type(BetaManagedAgentsFileDocumentSource.Type.FILE)
                .build()

        val roundtrippedBetaManagedAgentsFileDocumentSource =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsFileDocumentSource),
                jacksonTypeRef<BetaManagedAgentsFileDocumentSource>(),
            )

        assertThat(roundtrippedBetaManagedAgentsFileDocumentSource)
            .isEqualTo(betaManagedAgentsFileDocumentSource)
    }
}
