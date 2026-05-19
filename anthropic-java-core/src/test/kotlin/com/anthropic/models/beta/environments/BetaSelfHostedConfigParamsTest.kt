// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.environments

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaSelfHostedConfigParamsTest {

    @Test
    fun create() {
        val betaSelfHostedConfigParams = BetaSelfHostedConfigParams.builder().build()
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaSelfHostedConfigParams = BetaSelfHostedConfigParams.builder().build()

        val roundtrippedBetaSelfHostedConfigParams =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaSelfHostedConfigParams),
                jacksonTypeRef<BetaSelfHostedConfigParams>(),
            )

        assertThat(roundtrippedBetaSelfHostedConfigParams).isEqualTo(betaSelfHostedConfigParams)
    }
}
