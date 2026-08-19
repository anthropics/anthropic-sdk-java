// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaBrowserLeftMouseDownConfigTest {

    @Test
    fun create() {
        val betaBrowserLeftMouseDownConfig =
            BetaBrowserLeftMouseDownConfig.builder().deferLoading(true).enabled(true).build()

        assertThat(betaBrowserLeftMouseDownConfig.deferLoading()).contains(true)
        assertThat(betaBrowserLeftMouseDownConfig.enabled()).contains(true)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaBrowserLeftMouseDownConfig =
            BetaBrowserLeftMouseDownConfig.builder().deferLoading(true).enabled(true).build()

        val roundtrippedBetaBrowserLeftMouseDownConfig =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaBrowserLeftMouseDownConfig),
                jacksonTypeRef<BetaBrowserLeftMouseDownConfig>(),
            )

        assertThat(roundtrippedBetaBrowserLeftMouseDownConfig)
            .isEqualTo(betaBrowserLeftMouseDownConfig)
    }
}
