package com.anthropic.models.beta.sessions.resources

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsDeleteSessionResourceTest {

    @Test
    fun create() {
        val betaManagedAgentsDeleteSessionResource =
            BetaManagedAgentsDeleteSessionResource.of("sesrsc_011CZkZBJq5dWxk9fVLNcPht")

        assertThat(betaManagedAgentsDeleteSessionResource.id())
            .isEqualTo("sesrsc_011CZkZBJq5dWxk9fVLNcPht")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsDeleteSessionResource =
            BetaManagedAgentsDeleteSessionResource.of("sesrsc_011CZkZBJq5dWxk9fVLNcPht")

        val roundtrippedBetaManagedAgentsDeleteSessionResource =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsDeleteSessionResource),
                jacksonTypeRef<BetaManagedAgentsDeleteSessionResource>(),
            )

        assertThat(roundtrippedBetaManagedAgentsDeleteSessionResource)
            .isEqualTo(betaManagedAgentsDeleteSessionResource)
    }
}
