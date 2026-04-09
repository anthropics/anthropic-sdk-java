// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.sessions.events

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsFileImageSourceTest {

    @Test
    fun create() {
        val betaManagedAgentsFileImageSource =
            BetaManagedAgentsFileImageSource.builder()
                .fileId("x")
                .type(BetaManagedAgentsFileImageSource.Type.FILE)
                .build()

        assertThat(betaManagedAgentsFileImageSource.fileId()).isEqualTo("x")
        assertThat(betaManagedAgentsFileImageSource.type())
            .isEqualTo(BetaManagedAgentsFileImageSource.Type.FILE)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsFileImageSource =
            BetaManagedAgentsFileImageSource.builder()
                .fileId("x")
                .type(BetaManagedAgentsFileImageSource.Type.FILE)
                .build()

        val roundtrippedBetaManagedAgentsFileImageSource =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsFileImageSource),
                jacksonTypeRef<BetaManagedAgentsFileImageSource>(),
            )

        assertThat(roundtrippedBetaManagedAgentsFileImageSource)
            .isEqualTo(betaManagedAgentsFileImageSource)
    }
}
