package com.anthropic.models.beta.organization.rbacgroups

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaRbacGroupNotFoundErrorDetailsTest {

    @Test
    fun create() {
        val betaRbacGroupNotFoundErrorDetails = BetaRbacGroupNotFoundErrorDetails.builder().build()
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaRbacGroupNotFoundErrorDetails = BetaRbacGroupNotFoundErrorDetails.builder().build()

        val roundtrippedBetaRbacGroupNotFoundErrorDetails =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaRbacGroupNotFoundErrorDetails),
                jacksonTypeRef<BetaRbacGroupNotFoundErrorDetails>(),
            )

        assertThat(roundtrippedBetaRbacGroupNotFoundErrorDetails)
            .isEqualTo(betaRbacGroupNotFoundErrorDetails)
    }
}
