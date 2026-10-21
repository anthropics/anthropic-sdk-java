package com.anthropic.models.beta.memorystores.memoryversions

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsApiActorTest {

    @Test
    fun create() {
        val betaManagedAgentsApiActor = BetaManagedAgentsApiActor.of("x")

        assertThat(betaManagedAgentsApiActor.apiKeyId()).isEqualTo("x")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsApiActor = BetaManagedAgentsApiActor.of("x")

        val roundtrippedBetaManagedAgentsApiActor =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsApiActor),
                jacksonTypeRef<BetaManagedAgentsApiActor>(),
            )

        assertThat(roundtrippedBetaManagedAgentsApiActor).isEqualTo(betaManagedAgentsApiActor)
    }
}
