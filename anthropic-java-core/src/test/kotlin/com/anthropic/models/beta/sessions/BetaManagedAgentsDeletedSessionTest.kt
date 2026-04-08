// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.sessions

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsDeletedSessionTest {

    @Test
    fun create() {
        val betaManagedAgentsDeletedSession =
            BetaManagedAgentsDeletedSession.builder()
                .id("sesn_011CZkZAtmR3yMPDzynEDxu7")
                .type(BetaManagedAgentsDeletedSession.Type.SESSION_DELETED)
                .build()

        assertThat(betaManagedAgentsDeletedSession.id()).isEqualTo("sesn_011CZkZAtmR3yMPDzynEDxu7")
        assertThat(betaManagedAgentsDeletedSession.type())
            .isEqualTo(BetaManagedAgentsDeletedSession.Type.SESSION_DELETED)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsDeletedSession =
            BetaManagedAgentsDeletedSession.builder()
                .id("sesn_011CZkZAtmR3yMPDzynEDxu7")
                .type(BetaManagedAgentsDeletedSession.Type.SESSION_DELETED)
                .build()

        val roundtrippedBetaManagedAgentsDeletedSession =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsDeletedSession),
                jacksonTypeRef<BetaManagedAgentsDeletedSession>(),
            )

        assertThat(roundtrippedBetaManagedAgentsDeletedSession)
            .isEqualTo(betaManagedAgentsDeletedSession)
    }
}
