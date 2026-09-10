package com.anthropic.models.beta.agents

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsAutoPolicyTest {

    @Test
    fun create() {
        val betaManagedAgentsAutoPolicy = BetaManagedAgentsAutoPolicy.builder().build()
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsAutoPolicy = BetaManagedAgentsAutoPolicy.builder().build()

        val roundtrippedBetaManagedAgentsAutoPolicy =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsAutoPolicy),
                jacksonTypeRef<BetaManagedAgentsAutoPolicy>(),
            )

        assertThat(roundtrippedBetaManagedAgentsAutoPolicy).isEqualTo(betaManagedAgentsAutoPolicy)
    }
}
