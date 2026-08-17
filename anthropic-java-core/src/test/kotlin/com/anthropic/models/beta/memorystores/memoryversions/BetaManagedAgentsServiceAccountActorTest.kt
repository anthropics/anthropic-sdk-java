// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.memorystores.memoryversions

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsServiceAccountActorTest {

    @Test
    fun create() {
        val betaManagedAgentsServiceAccountActor = BetaManagedAgentsServiceAccountActor.of("x")

        assertThat(betaManagedAgentsServiceAccountActor.serviceAccountId()).isEqualTo("x")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsServiceAccountActor = BetaManagedAgentsServiceAccountActor.of("x")

        val roundtrippedBetaManagedAgentsServiceAccountActor =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsServiceAccountActor),
                jacksonTypeRef<BetaManagedAgentsServiceAccountActor>(),
            )

        assertThat(roundtrippedBetaManagedAgentsServiceAccountActor)
            .isEqualTo(betaManagedAgentsServiceAccountActor)
    }
}
