// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.sessions.events

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsBase64DocumentSourceTest {

    @Test
    fun create() {
        val betaManagedAgentsBase64DocumentSource =
            BetaManagedAgentsBase64DocumentSource.builder()
                .data("x")
                .mediaType("x")
                .type(BetaManagedAgentsBase64DocumentSource.Type.BASE64)
                .build()

        assertThat(betaManagedAgentsBase64DocumentSource.data()).isEqualTo("x")
        assertThat(betaManagedAgentsBase64DocumentSource.mediaType()).isEqualTo("x")
        assertThat(betaManagedAgentsBase64DocumentSource.type())
            .isEqualTo(BetaManagedAgentsBase64DocumentSource.Type.BASE64)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsBase64DocumentSource =
            BetaManagedAgentsBase64DocumentSource.builder()
                .data("x")
                .mediaType("x")
                .type(BetaManagedAgentsBase64DocumentSource.Type.BASE64)
                .build()

        val roundtrippedBetaManagedAgentsBase64DocumentSource =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsBase64DocumentSource),
                jacksonTypeRef<BetaManagedAgentsBase64DocumentSource>(),
            )

        assertThat(roundtrippedBetaManagedAgentsBase64DocumentSource)
            .isEqualTo(betaManagedAgentsBase64DocumentSource)
    }
}
