package com.anthropic.models.beta.agents

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsWebFetchUrlSourceExceptTest {

    @Test
    fun create() {
        val betaManagedAgentsWebFetchUrlSourceExcept =
            BetaManagedAgentsWebFetchUrlSourceExcept.builder()
                .addTool(BetaManagedAgentsWebFetchUrlSourceToolReference.of("x"))
                .build()

        assertThat(betaManagedAgentsWebFetchUrlSourceExcept.tools())
            .containsExactly(BetaManagedAgentsWebFetchUrlSourceToolReference.of("x"))
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsWebFetchUrlSourceExcept =
            BetaManagedAgentsWebFetchUrlSourceExcept.builder()
                .addTool(BetaManagedAgentsWebFetchUrlSourceToolReference.of("x"))
                .build()

        val roundtrippedBetaManagedAgentsWebFetchUrlSourceExcept =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsWebFetchUrlSourceExcept),
                jacksonTypeRef<BetaManagedAgentsWebFetchUrlSourceExcept>(),
            )

        assertThat(roundtrippedBetaManagedAgentsWebFetchUrlSourceExcept)
            .isEqualTo(betaManagedAgentsWebFetchUrlSourceExcept)
    }
}
