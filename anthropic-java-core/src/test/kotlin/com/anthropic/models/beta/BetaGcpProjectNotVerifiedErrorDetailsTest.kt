package com.anthropic.models.beta

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaGcpProjectNotVerifiedErrorDetailsTest {

    @Test
    fun create() {
        val betaGcpProjectNotVerifiedErrorDetails =
            BetaGcpProjectNotVerifiedErrorDetails.builder().build()
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaGcpProjectNotVerifiedErrorDetails =
            BetaGcpProjectNotVerifiedErrorDetails.builder().build()

        val roundtrippedBetaGcpProjectNotVerifiedErrorDetails =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaGcpProjectNotVerifiedErrorDetails),
                jacksonTypeRef<BetaGcpProjectNotVerifiedErrorDetails>(),
            )

        assertThat(roundtrippedBetaGcpProjectNotVerifiedErrorDetails)
            .isEqualTo(betaGcpProjectNotVerifiedErrorDetails)
    }
}
