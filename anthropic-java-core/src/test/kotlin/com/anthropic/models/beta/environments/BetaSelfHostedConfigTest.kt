// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.environments

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaSelfHostedConfigTest {

    @Test
    fun create() {
        val betaSelfHostedConfig = BetaSelfHostedConfig.builder().build()
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaSelfHostedConfig = BetaSelfHostedConfig.builder().build()

        val roundtrippedBetaSelfHostedConfig =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaSelfHostedConfig),
                jacksonTypeRef<BetaSelfHostedConfig>(),
            )

        assertThat(roundtrippedBetaSelfHostedConfig).isEqualTo(betaSelfHostedConfig)
    }
}
