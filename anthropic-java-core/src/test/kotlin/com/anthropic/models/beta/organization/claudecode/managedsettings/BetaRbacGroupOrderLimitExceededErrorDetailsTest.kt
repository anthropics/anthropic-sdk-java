package com.anthropic.models.beta.organization.claudecode.managedsettings

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaRbacGroupOrderLimitExceededErrorDetailsTest {

    @Test
    fun create() {
        val betaRbacGroupOrderLimitExceededErrorDetails =
            BetaRbacGroupOrderLimitExceededErrorDetails.builder().build()
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaRbacGroupOrderLimitExceededErrorDetails =
            BetaRbacGroupOrderLimitExceededErrorDetails.builder().build()

        val roundtrippedBetaRbacGroupOrderLimitExceededErrorDetails =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaRbacGroupOrderLimitExceededErrorDetails),
                jacksonTypeRef<BetaRbacGroupOrderLimitExceededErrorDetails>(),
            )

        assertThat(roundtrippedBetaRbacGroupOrderLimitExceededErrorDetails)
            .isEqualTo(betaRbacGroupOrderLimitExceededErrorDetails)
    }
}
