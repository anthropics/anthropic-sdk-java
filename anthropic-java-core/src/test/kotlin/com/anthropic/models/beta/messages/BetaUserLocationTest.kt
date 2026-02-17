// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaUserLocationTest {

    @Test
    fun create() {
        val betaUserLocation =
            BetaUserLocation.builder()
                .city("New York")
                .country("US")
                .region("California")
                .timezone("America/New_York")
                .build()

        assertThat(betaUserLocation.city()).contains("New York")
        assertThat(betaUserLocation.country()).contains("US")
        assertThat(betaUserLocation.region()).contains("California")
        assertThat(betaUserLocation.timezone()).contains("America/New_York")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaUserLocation =
            BetaUserLocation.builder()
                .city("New York")
                .country("US")
                .region("California")
                .timezone("America/New_York")
                .build()

        val roundtrippedBetaUserLocation =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaUserLocation),
                jacksonTypeRef<BetaUserLocation>(),
            )

        assertThat(roundtrippedBetaUserLocation).isEqualTo(betaUserLocation)
    }
}
