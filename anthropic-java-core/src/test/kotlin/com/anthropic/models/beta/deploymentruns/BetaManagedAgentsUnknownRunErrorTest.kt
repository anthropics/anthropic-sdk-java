// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.deploymentruns

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsUnknownRunErrorTest {

    @Test
    fun create() {
        val betaManagedAgentsUnknownRunError =
            BetaManagedAgentsUnknownRunError.builder()
                .message("message")
                .type(BetaManagedAgentsUnknownRunError.Type.UNKNOWN_ERROR)
                .build()

        assertThat(betaManagedAgentsUnknownRunError.message()).isEqualTo("message")
        assertThat(betaManagedAgentsUnknownRunError.type())
            .isEqualTo(BetaManagedAgentsUnknownRunError.Type.UNKNOWN_ERROR)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsUnknownRunError =
            BetaManagedAgentsUnknownRunError.builder()
                .message("message")
                .type(BetaManagedAgentsUnknownRunError.Type.UNKNOWN_ERROR)
                .build()

        val roundtrippedBetaManagedAgentsUnknownRunError =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsUnknownRunError),
                jacksonTypeRef<BetaManagedAgentsUnknownRunError>(),
            )

        assertThat(roundtrippedBetaManagedAgentsUnknownRunError)
            .isEqualTo(betaManagedAgentsUnknownRunError)
    }
}
