// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaBrowserHoldKeyConfigTest {

    @Test
    fun create() {
        val betaBrowserHoldKeyConfig =
            BetaBrowserHoldKeyConfig.builder().deferLoading(true).enabled(true).build()

        assertThat(betaBrowserHoldKeyConfig.deferLoading()).contains(true)
        assertThat(betaBrowserHoldKeyConfig.enabled()).contains(true)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaBrowserHoldKeyConfig =
            BetaBrowserHoldKeyConfig.builder().deferLoading(true).enabled(true).build()

        val roundtrippedBetaBrowserHoldKeyConfig =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaBrowserHoldKeyConfig),
                jacksonTypeRef<BetaBrowserHoldKeyConfig>(),
            )

        assertThat(roundtrippedBetaBrowserHoldKeyConfig).isEqualTo(betaBrowserHoldKeyConfig)
    }
}
