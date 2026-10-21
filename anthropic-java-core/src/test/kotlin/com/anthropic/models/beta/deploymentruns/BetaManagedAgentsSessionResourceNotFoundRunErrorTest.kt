package com.anthropic.models.beta.deploymentruns

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsSessionResourceNotFoundRunErrorTest {

    @Test
    fun create() {
        val betaManagedAgentsSessionResourceNotFoundRunError =
            BetaManagedAgentsSessionResourceNotFoundRunError.of("message")

        assertThat(betaManagedAgentsSessionResourceNotFoundRunError.message()).isEqualTo("message")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsSessionResourceNotFoundRunError =
            BetaManagedAgentsSessionResourceNotFoundRunError.of("message")

        val roundtrippedBetaManagedAgentsSessionResourceNotFoundRunError =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsSessionResourceNotFoundRunError),
                jacksonTypeRef<BetaManagedAgentsSessionResourceNotFoundRunError>(),
            )

        assertThat(roundtrippedBetaManagedAgentsSessionResourceNotFoundRunError)
            .isEqualTo(betaManagedAgentsSessionResourceNotFoundRunError)
    }
}
