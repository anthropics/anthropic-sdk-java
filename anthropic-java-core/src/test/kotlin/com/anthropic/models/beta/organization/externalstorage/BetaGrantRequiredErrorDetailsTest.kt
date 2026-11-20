package com.anthropic.models.beta.organization.externalstorage

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaGrantRequiredErrorDetailsTest {

    @Test
    fun create() {
        val betaGrantRequiredErrorDetails = BetaGrantRequiredErrorDetails.builder().build()
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaGrantRequiredErrorDetails = BetaGrantRequiredErrorDetails.builder().build()

        val roundtrippedBetaGrantRequiredErrorDetails =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaGrantRequiredErrorDetails),
                jacksonTypeRef<BetaGrantRequiredErrorDetails>(),
            )

        assertThat(roundtrippedBetaGrantRequiredErrorDetails)
            .isEqualTo(betaGrantRequiredErrorDetails)
    }
}
