// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaBrowserKeyConfigTest {

    @Test
    fun create() {
        val betaBrowserKeyConfig =
            BetaBrowserKeyConfig.builder().deferLoading(true).enabled(true).build()

        assertThat(betaBrowserKeyConfig.deferLoading()).contains(true)
        assertThat(betaBrowserKeyConfig.enabled()).contains(true)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaBrowserKeyConfig =
            BetaBrowserKeyConfig.builder().deferLoading(true).enabled(true).build()

        val roundtrippedBetaBrowserKeyConfig =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaBrowserKeyConfig),
                jacksonTypeRef<BetaBrowserKeyConfig>(),
            )

        assertThat(roundtrippedBetaBrowserKeyConfig).isEqualTo(betaBrowserKeyConfig)
    }
}
