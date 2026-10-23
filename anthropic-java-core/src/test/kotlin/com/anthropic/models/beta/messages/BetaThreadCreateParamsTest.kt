package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaThreadCreateParamsTest {

    @Test
    fun create() {
        val betaThreadCreateParams = BetaThreadCreateParams.builder().build()
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaThreadCreateParams = BetaThreadCreateParams.builder().build()

        val roundtrippedBetaThreadCreateParams =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaThreadCreateParams),
                jacksonTypeRef<BetaThreadCreateParams>(),
            )

        assertThat(roundtrippedBetaThreadCreateParams).isEqualTo(betaThreadCreateParams)
    }
}
