package com.anthropic.models.beta.organization.claudetag

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaApiKeyScopeMissingErrorDetailsTest {

    @Test
    fun create() {
        val betaApiKeyScopeMissingErrorDetails =
            BetaApiKeyScopeMissingErrorDetails.builder().build()
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaApiKeyScopeMissingErrorDetails =
            BetaApiKeyScopeMissingErrorDetails.builder().build()

        val roundtrippedBetaApiKeyScopeMissingErrorDetails =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaApiKeyScopeMissingErrorDetails),
                jacksonTypeRef<BetaApiKeyScopeMissingErrorDetails>(),
            )

        assertThat(roundtrippedBetaApiKeyScopeMissingErrorDetails)
            .isEqualTo(betaApiKeyScopeMissingErrorDetails)
    }
}
