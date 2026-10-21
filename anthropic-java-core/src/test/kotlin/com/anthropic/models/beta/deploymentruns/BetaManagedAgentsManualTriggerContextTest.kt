package com.anthropic.models.beta.deploymentruns

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsManualTriggerContextTest {

    @Test
    fun create() {
        val betaManagedAgentsManualTriggerContext =
            BetaManagedAgentsManualTriggerContext.builder().build()
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsManualTriggerContext =
            BetaManagedAgentsManualTriggerContext.builder().build()

        val roundtrippedBetaManagedAgentsManualTriggerContext =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsManualTriggerContext),
                jacksonTypeRef<BetaManagedAgentsManualTriggerContext>(),
            )

        assertThat(roundtrippedBetaManagedAgentsManualTriggerContext)
            .isEqualTo(betaManagedAgentsManualTriggerContext)
    }
}
