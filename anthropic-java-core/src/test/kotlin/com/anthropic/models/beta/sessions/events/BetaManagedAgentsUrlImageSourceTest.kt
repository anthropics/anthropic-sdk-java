// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.sessions.events

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsUrlImageSourceTest {

    @Test
    fun create() {
        val betaManagedAgentsUrlImageSource =
            BetaManagedAgentsUrlImageSource.builder()
                .type(BetaManagedAgentsUrlImageSource.Type.URL)
                .url("url")
                .build()

        assertThat(betaManagedAgentsUrlImageSource.type())
            .isEqualTo(BetaManagedAgentsUrlImageSource.Type.URL)
        assertThat(betaManagedAgentsUrlImageSource.url()).isEqualTo("url")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsUrlImageSource =
            BetaManagedAgentsUrlImageSource.builder()
                .type(BetaManagedAgentsUrlImageSource.Type.URL)
                .url("url")
                .build()

        val roundtrippedBetaManagedAgentsUrlImageSource =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsUrlImageSource),
                jacksonTypeRef<BetaManagedAgentsUrlImageSource>(),
            )

        assertThat(roundtrippedBetaManagedAgentsUrlImageSource)
            .isEqualTo(betaManagedAgentsUrlImageSource)
    }
}
