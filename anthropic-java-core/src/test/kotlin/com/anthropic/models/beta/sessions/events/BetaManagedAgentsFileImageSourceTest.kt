package com.anthropic.models.beta.sessions.events

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsFileImageSourceTest {

    @Test
    fun create() {
        val betaManagedAgentsFileImageSource = BetaManagedAgentsFileImageSource.of("x")

        assertThat(betaManagedAgentsFileImageSource.fileId()).isEqualTo("x")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsFileImageSource = BetaManagedAgentsFileImageSource.of("x")

        val roundtrippedBetaManagedAgentsFileImageSource =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsFileImageSource),
                jacksonTypeRef<BetaManagedAgentsFileImageSource>(),
            )

        assertThat(roundtrippedBetaManagedAgentsFileImageSource)
            .isEqualTo(betaManagedAgentsFileImageSource)
    }
}
