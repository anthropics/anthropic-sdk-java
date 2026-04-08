// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.sessions.resources

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsDeleteSessionResourceTest {

    @Test
    fun create() {
        val betaManagedAgentsDeleteSessionResource =
            BetaManagedAgentsDeleteSessionResource.builder()
                .id("sesrsc_011CZkZBJq5dWxk9fVLNcPht")
                .type(BetaManagedAgentsDeleteSessionResource.Type.SESSION_RESOURCE_DELETED)
                .build()

        assertThat(betaManagedAgentsDeleteSessionResource.id())
            .isEqualTo("sesrsc_011CZkZBJq5dWxk9fVLNcPht")
        assertThat(betaManagedAgentsDeleteSessionResource.type())
            .isEqualTo(BetaManagedAgentsDeleteSessionResource.Type.SESSION_RESOURCE_DELETED)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsDeleteSessionResource =
            BetaManagedAgentsDeleteSessionResource.builder()
                .id("sesrsc_011CZkZBJq5dWxk9fVLNcPht")
                .type(BetaManagedAgentsDeleteSessionResource.Type.SESSION_RESOURCE_DELETED)
                .build()

        val roundtrippedBetaManagedAgentsDeleteSessionResource =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsDeleteSessionResource),
                jacksonTypeRef<BetaManagedAgentsDeleteSessionResource>(),
            )

        assertThat(roundtrippedBetaManagedAgentsDeleteSessionResource)
            .isEqualTo(betaManagedAgentsDeleteSessionResource)
    }
}
