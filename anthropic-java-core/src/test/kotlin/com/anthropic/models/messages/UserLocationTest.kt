// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class UserLocationTest {

    @Test
    fun create() {
        val userLocation =
            UserLocation.builder()
                .city("New York")
                .country("US")
                .region("California")
                .timezone("America/New_York")
                .build()

        assertThat(userLocation.city()).contains("New York")
        assertThat(userLocation.country()).contains("US")
        assertThat(userLocation.region()).contains("California")
        assertThat(userLocation.timezone()).contains("America/New_York")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val userLocation =
            UserLocation.builder()
                .city("New York")
                .country("US")
                .region("California")
                .timezone("America/New_York")
                .build()

        val roundtrippedUserLocation =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(userLocation),
                jacksonTypeRef<UserLocation>(),
            )

        assertThat(roundtrippedUserLocation).isEqualTo(userLocation)
    }
}
