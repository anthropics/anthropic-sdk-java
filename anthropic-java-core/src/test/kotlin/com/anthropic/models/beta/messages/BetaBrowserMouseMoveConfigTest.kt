// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaBrowserMouseMoveConfigTest {

    @Test
    fun create() {
        val betaBrowserMouseMoveConfig =
            BetaBrowserMouseMoveConfig.builder().deferLoading(true).enabled(true).build()

        assertThat(betaBrowserMouseMoveConfig.deferLoading()).contains(true)
        assertThat(betaBrowserMouseMoveConfig.enabled()).contains(true)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaBrowserMouseMoveConfig =
            BetaBrowserMouseMoveConfig.builder().deferLoading(true).enabled(true).build()

        val roundtrippedBetaBrowserMouseMoveConfig =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaBrowserMouseMoveConfig),
                jacksonTypeRef<BetaBrowserMouseMoveConfig>(),
            )

        assertThat(roundtrippedBetaBrowserMouseMoveConfig).isEqualTo(betaBrowserMouseMoveConfig)
    }
}
