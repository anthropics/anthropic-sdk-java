// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.deploymentruns

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsSelfHostedResourcesUnsupportedRunErrorTest {

    @Test
    fun create() {
        val betaManagedAgentsSelfHostedResourcesUnsupportedRunError =
            BetaManagedAgentsSelfHostedResourcesUnsupportedRunError.builder()
                .message("message")
                .type(
                    BetaManagedAgentsSelfHostedResourcesUnsupportedRunError.Type
                        .SELF_HOSTED_RESOURCES_UNSUPPORTED_ERROR
                )
                .build()

        assertThat(betaManagedAgentsSelfHostedResourcesUnsupportedRunError.message())
            .isEqualTo("message")
        assertThat(betaManagedAgentsSelfHostedResourcesUnsupportedRunError.type())
            .isEqualTo(
                BetaManagedAgentsSelfHostedResourcesUnsupportedRunError.Type
                    .SELF_HOSTED_RESOURCES_UNSUPPORTED_ERROR
            )
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsSelfHostedResourcesUnsupportedRunError =
            BetaManagedAgentsSelfHostedResourcesUnsupportedRunError.builder()
                .message("message")
                .type(
                    BetaManagedAgentsSelfHostedResourcesUnsupportedRunError.Type
                        .SELF_HOSTED_RESOURCES_UNSUPPORTED_ERROR
                )
                .build()

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
