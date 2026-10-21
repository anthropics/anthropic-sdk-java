package com.anthropic.models.beta.memorystores.memoryversions

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsSessionActorTest {

    @Test
    fun create() {
        val betaManagedAgentsSessionActor = BetaManagedAgentsSessionActor.of("x")

        assertThat(betaManagedAgentsSessionActor.sessionId()).isEqualTo("x")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsSessionActor = BetaManagedAgentsSessionActor.of("x")

        val roundtrippedBetaManagedAgentsSessionActor =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsSessionActor),
                jacksonTypeRef<BetaManagedAgentsSessionActor>(),
            )

        assertThat(roundtrippedBetaManagedAgentsSessionActor)
            .isEqualTo(betaManagedAgentsSessionActor)
    }
}
