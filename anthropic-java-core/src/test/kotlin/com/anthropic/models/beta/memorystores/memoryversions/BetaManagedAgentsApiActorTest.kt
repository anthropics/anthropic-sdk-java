// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.memorystores.memoryversions

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsApiActorTest {

    @Test
    fun create() {
        val betaManagedAgentsApiActor =
            BetaManagedAgentsApiActor.builder()
                .apiKeyId("x")
                .type(BetaManagedAgentsApiActor.Type.API_ACTOR)
                .build()

        assertThat(betaManagedAgentsApiActor.apiKeyId()).isEqualTo("x")
        assertThat(betaManagedAgentsApiActor.type())
            .isEqualTo(BetaManagedAgentsApiActor.Type.API_ACTOR)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsApiActor =
            BetaManagedAgentsApiActor.builder()
                .apiKeyId("x")
                .type(BetaManagedAgentsApiActor.Type.API_ACTOR)
                .build()

        val roundtrippedBetaManagedAgentsApiActor =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsApiActor),
                jacksonTypeRef<BetaManagedAgentsApiActor>(),
            )

        assertThat(roundtrippedBetaManagedAgentsApiActor).isEqualTo(betaManagedAgentsApiActor)
    }
}
