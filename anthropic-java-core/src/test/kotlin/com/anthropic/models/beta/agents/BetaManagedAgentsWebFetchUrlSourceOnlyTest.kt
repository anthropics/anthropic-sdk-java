package com.anthropic.models.beta.agents

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsWebFetchUrlSourceOnlyTest {

    @Test
    fun create() {
        val betaManagedAgentsWebFetchUrlSourceOnly =
            BetaManagedAgentsWebFetchUrlSourceOnly.builder()
                .addTool(BetaManagedAgentsWebFetchUrlSourceToolReference.of("x"))
                .build()

        assertThat(betaManagedAgentsWebFetchUrlSourceOnly.tools())
            .containsExactly(BetaManagedAgentsWebFetchUrlSourceToolReference.of("x"))
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsWebFetchUrlSourceOnly =
            BetaManagedAgentsWebFetchUrlSourceOnly.builder()
                .addTool(BetaManagedAgentsWebFetchUrlSourceToolReference.of("x"))
                .build()

        val roundtrippedBetaManagedAgentsWebFetchUrlSourceOnly =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsWebFetchUrlSourceOnly),
                jacksonTypeRef<BetaManagedAgentsWebFetchUrlSourceOnly>(),
            )

        assertThat(roundtrippedBetaManagedAgentsWebFetchUrlSourceOnly)
            .isEqualTo(betaManagedAgentsWebFetchUrlSourceOnly)
    }
}
