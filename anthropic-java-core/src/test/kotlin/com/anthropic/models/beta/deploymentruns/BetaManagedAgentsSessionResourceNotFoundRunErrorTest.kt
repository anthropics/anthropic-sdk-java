// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.deploymentruns

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsSessionResourceNotFoundRunErrorTest {

    @Test
    fun create() {
        val betaManagedAgentsSessionResourceNotFoundRunError =
            BetaManagedAgentsSessionResourceNotFoundRunError.builder()
                .message("message")
                .type(
                    BetaManagedAgentsSessionResourceNotFoundRunError.Type
                        .SESSION_RESOURCE_NOT_FOUND_ERROR
                )
                .build()

        assertThat(betaManagedAgentsSessionResourceNotFoundRunError.message()).isEqualTo("message")
        assertThat(betaManagedAgentsSessionResourceNotFoundRunError.type())
            .isEqualTo(
                BetaManagedAgentsSessionResourceNotFoundRunError.Type
                    .SESSION_RESOURCE_NOT_FOUND_ERROR
            )
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsSessionResourceNotFoundRunError =
            BetaManagedAgentsSessionResourceNotFoundRunError.builder()
                .message("message")
                .type(
                    BetaManagedAgentsSessionResourceNotFoundRunError.Type
                        .SESSION_RESOURCE_NOT_FOUND_ERROR
                )
                .build()

        val roundtrippedBetaManagedAgentsSessionResourceNotFoundRunError =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsSessionResourceNotFoundRunError),
                jacksonTypeRef<BetaManagedAgentsSessionResourceNotFoundRunError>(),
            )

        assertThat(roundtrippedBetaManagedAgentsSessionResourceNotFoundRunError)
            .isEqualTo(betaManagedAgentsSessionResourceNotFoundRunError)
    }
}
