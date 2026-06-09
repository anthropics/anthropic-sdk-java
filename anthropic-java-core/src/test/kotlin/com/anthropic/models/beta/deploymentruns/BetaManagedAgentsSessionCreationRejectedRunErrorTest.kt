// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.deploymentruns

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsSessionCreationRejectedRunErrorTest {

    @Test
    fun create() {
        val betaManagedAgentsSessionCreationRejectedRunError =
            BetaManagedAgentsSessionCreationRejectedRunError.builder()
                .message("message")
                .type(
                    BetaManagedAgentsSessionCreationRejectedRunError.Type
                        .SESSION_CREATION_REJECTED_ERROR
                )
                .build()

        assertThat(betaManagedAgentsSessionCreationRejectedRunError.message()).isEqualTo("message")
        assertThat(betaManagedAgentsSessionCreationRejectedRunError.type())
            .isEqualTo(
                BetaManagedAgentsSessionCreationRejectedRunError.Type
                    .SESSION_CREATION_REJECTED_ERROR
            )
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsSessionCreationRejectedRunError =
            BetaManagedAgentsSessionCreationRejectedRunError.builder()
                .message("message")
                .type(
                    BetaManagedAgentsSessionCreationRejectedRunError.Type
                        .SESSION_CREATION_REJECTED_ERROR
                )
                .build()

        val roundtrippedBetaManagedAgentsSessionCreationRejectedRunError =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsSessionCreationRejectedRunError),
                jacksonTypeRef<BetaManagedAgentsSessionCreationRejectedRunError>(),
            )

        assertThat(roundtrippedBetaManagedAgentsSessionCreationRejectedRunError)
            .isEqualTo(betaManagedAgentsSessionCreationRejectedRunError)
    }
}
