package com.anthropic.models.beta.agents

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsAlwaysAskPolicyTest {

    @Test
    fun create() {
        val betaManagedAgentsAlwaysAskPolicy = BetaManagedAgentsAlwaysAskPolicy.builder().build()
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsAlwaysAskPolicy = BetaManagedAgentsAlwaysAskPolicy.builder().build()

        val roundtrippedBetaManagedAgentsAlwaysAskPolicy =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsAlwaysAskPolicy),
                jacksonTypeRef<BetaManagedAgentsAlwaysAskPolicy>(),
            )

        assertThat(roundtrippedBetaManagedAgentsAlwaysAskPolicy)
            .isEqualTo(betaManagedAgentsAlwaysAskPolicy)
    }
}
