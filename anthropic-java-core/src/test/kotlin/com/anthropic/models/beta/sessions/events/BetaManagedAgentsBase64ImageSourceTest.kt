// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.sessions.events

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsBase64ImageSourceTest {

    @Test
    fun create() {
        val betaManagedAgentsBase64ImageSource =
            BetaManagedAgentsBase64ImageSource.builder()
                .data("x")
                .mediaType("x")
                .type(BetaManagedAgentsBase64ImageSource.Type.BASE64)
                .build()

        assertThat(betaManagedAgentsBase64ImageSource.data()).isEqualTo("x")
        assertThat(betaManagedAgentsBase64ImageSource.mediaType()).isEqualTo("x")
        assertThat(betaManagedAgentsBase64ImageSource.type())
            .isEqualTo(BetaManagedAgentsBase64ImageSource.Type.BASE64)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsBase64ImageSource =
            BetaManagedAgentsBase64ImageSource.builder()
                .data("x")
                .mediaType("x")
                .type(BetaManagedAgentsBase64ImageSource.Type.BASE64)
                .build()

        val roundtrippedBetaManagedAgentsBase64ImageSource =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsBase64ImageSource),
                jacksonTypeRef<BetaManagedAgentsBase64ImageSource>(),
            )

        assertThat(roundtrippedBetaManagedAgentsBase64ImageSource)
            .isEqualTo(betaManagedAgentsBase64ImageSource)
    }
}
