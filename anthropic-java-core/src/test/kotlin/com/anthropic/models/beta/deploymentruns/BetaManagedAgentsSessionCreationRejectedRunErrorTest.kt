package com.anthropic.models.beta.deploymentruns

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsSessionCreationRejectedRunErrorTest {

    @Test
    fun create() {
        val betaManagedAgentsSessionCreationRejectedRunError =
            BetaManagedAgentsSessionCreationRejectedRunError.of("message")

        assertThat(betaManagedAgentsSessionCreationRejectedRunError.message()).isEqualTo("message")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsSessionCreationRejectedRunError =
            BetaManagedAgentsSessionCreationRejectedRunError.of("message")

        val roundtrippedBetaManagedAgentsSessionCreationRejectedRunError =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsSessionCreationRejectedRunError),
                jacksonTypeRef<BetaManagedAgentsSessionCreationRejectedRunError>(),
            )

        assertThat(roundtrippedBetaManagedAgentsSessionCreationRejectedRunError)
            .isEqualTo(betaManagedAgentsSessionCreationRejectedRunError)
    }
}
