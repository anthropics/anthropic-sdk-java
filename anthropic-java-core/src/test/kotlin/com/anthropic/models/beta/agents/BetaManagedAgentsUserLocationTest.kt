// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.agents

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsUserLocationTest {

    @Test
    fun create() {
        val betaManagedAgentsUserLocation =
            BetaManagedAgentsUserLocation.builder()
                .city("x")
                .country("country")
                .region("x")
                .timezone("x")
                .build()

        assertThat(betaManagedAgentsUserLocation.city()).contains("x")
        assertThat(betaManagedAgentsUserLocation.country()).contains("country")
        assertThat(betaManagedAgentsUserLocation.region()).contains("x")
        assertThat(betaManagedAgentsUserLocation.timezone()).contains("x")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsUserLocation =
            BetaManagedAgentsUserLocation.builder()
                .city("x")
                .country("country")
                .region("x")
                .timezone("x")
                .build()

        val roundtrippedBetaManagedAgentsUserLocation =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsUserLocation),
                jacksonTypeRef<BetaManagedAgentsUserLocation>(),
            )

        assertThat(roundtrippedBetaManagedAgentsUserLocation)
            .isEqualTo(betaManagedAgentsUserLocation)
    }
}
