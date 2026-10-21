package com.anthropic.models.beta.deploymentruns

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsSelfHostedResourcesUnsupportedRunErrorTest {

    @Test
    fun create() {
        val betaManagedAgentsSelfHostedResourcesUnsupportedRunError =
            BetaManagedAgentsSelfHostedResourcesUnsupportedRunError.of("message")

        assertThat(betaManagedAgentsSelfHostedResourcesUnsupportedRunError.message())
            .isEqualTo("message")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsSelfHostedResourcesUnsupportedRunError =
            BetaManagedAgentsSelfHostedResourcesUnsupportedRunError.of("message")

        val roundtrippedBetaManagedAgentsSelfHostedResourcesUnsupportedRunError =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(
                    betaManagedAgentsSelfHostedResourcesUnsupportedRunError
                ),
                jacksonTypeRef<BetaManagedAgentsSelfHostedResourcesUnsupportedRunError>(),
            )

        assertThat(roundtrippedBetaManagedAgentsSelfHostedResourcesUnsupportedRunError)
            .isEqualTo(betaManagedAgentsSelfHostedResourcesUnsupportedRunError)
    }
}
