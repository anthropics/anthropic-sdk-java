package com.anthropic.models.beta.memorystores.memoryversions

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsUserActorTest {

    @Test
    fun create() {
        val betaManagedAgentsUserActor = BetaManagedAgentsUserActor.of("x")

        assertThat(betaManagedAgentsUserActor.userId()).isEqualTo("x")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsUserActor = BetaManagedAgentsUserActor.of("x")

        val roundtrippedBetaManagedAgentsUserActor =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsUserActor),
                jacksonTypeRef<BetaManagedAgentsUserActor>(),
            )

        assertThat(roundtrippedBetaManagedAgentsUserActor).isEqualTo(betaManagedAgentsUserActor)
    }
}
