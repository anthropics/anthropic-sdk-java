// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.memorystores.memoryversions

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsSessionActorTest {

    @Test
    fun create() {
        val betaManagedAgentsSessionActor =
            BetaManagedAgentsSessionActor.builder()
                .sessionId("x")
                .type(BetaManagedAgentsSessionActor.Type.SESSION_ACTOR)
                .build()

        assertThat(betaManagedAgentsSessionActor.sessionId()).isEqualTo("x")
        assertThat(betaManagedAgentsSessionActor.type())
            .isEqualTo(BetaManagedAgentsSessionActor.Type.SESSION_ACTOR)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsSessionActor =
            BetaManagedAgentsSessionActor.builder()
                .sessionId("x")
                .type(BetaManagedAgentsSessionActor.Type.SESSION_ACTOR)
                .build()

        val roundtrippedBetaManagedAgentsSessionActor =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsSessionActor),
                jacksonTypeRef<BetaManagedAgentsSessionActor>(),
            )

        assertThat(roundtrippedBetaManagedAgentsSessionActor)
            .isEqualTo(betaManagedAgentsSessionActor)
    }
}
