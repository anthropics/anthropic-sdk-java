package com.anthropic.models.beta.agents

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsWebFetchUrlSourceToolReferenceTest {

    @Test
    fun create() {
        val betaManagedAgentsWebFetchUrlSourceToolReference =
            BetaManagedAgentsWebFetchUrlSourceToolReference.of("x")

        assertThat(betaManagedAgentsWebFetchUrlSourceToolReference.name()).isEqualTo("x")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsWebFetchUrlSourceToolReference =
            BetaManagedAgentsWebFetchUrlSourceToolReference.of("x")

        val roundtrippedBetaManagedAgentsWebFetchUrlSourceToolReference =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsWebFetchUrlSourceToolReference),
                jacksonTypeRef<BetaManagedAgentsWebFetchUrlSourceToolReference>(),
            )

        assertThat(roundtrippedBetaManagedAgentsWebFetchUrlSourceToolReference)
            .isEqualTo(betaManagedAgentsWebFetchUrlSourceToolReference)
    }
}
