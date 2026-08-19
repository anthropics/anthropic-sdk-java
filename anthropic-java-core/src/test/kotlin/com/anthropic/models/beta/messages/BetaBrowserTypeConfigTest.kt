// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaBrowserTypeConfigTest {

    @Test
    fun create() {
        val betaBrowserTypeConfig =
            BetaBrowserTypeConfig.builder().deferLoading(true).enabled(true).build()

        assertThat(betaBrowserTypeConfig.deferLoading()).contains(true)
        assertThat(betaBrowserTypeConfig.enabled()).contains(true)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaBrowserTypeConfig =
            BetaBrowserTypeConfig.builder().deferLoading(true).enabled(true).build()

        val roundtrippedBetaBrowserTypeConfig =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaBrowserTypeConfig),
                jacksonTypeRef<BetaBrowserTypeConfig>(),
            )

        assertThat(roundtrippedBetaBrowserTypeConfig).isEqualTo(betaBrowserTypeConfig)
    }
}
