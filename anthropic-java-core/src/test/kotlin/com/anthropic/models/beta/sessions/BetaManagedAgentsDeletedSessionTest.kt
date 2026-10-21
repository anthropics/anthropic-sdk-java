package com.anthropic.models.beta.sessions

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsDeletedSessionTest {

    @Test
    fun create() {
        val betaManagedAgentsDeletedSession =
            BetaManagedAgentsDeletedSession.of("sesn_011CZkZAtmR3yMPDzynEDxu7")

        assertThat(betaManagedAgentsDeletedSession.id()).isEqualTo("sesn_011CZkZAtmR3yMPDzynEDxu7")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsDeletedSession =
            BetaManagedAgentsDeletedSession.of("sesn_011CZkZAtmR3yMPDzynEDxu7")

        val roundtrippedBetaManagedAgentsDeletedSession =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsDeletedSession),
                jacksonTypeRef<BetaManagedAgentsDeletedSession>(),
            )

        assertThat(roundtrippedBetaManagedAgentsDeletedSession)
            .isEqualTo(betaManagedAgentsDeletedSession)
    }
}
