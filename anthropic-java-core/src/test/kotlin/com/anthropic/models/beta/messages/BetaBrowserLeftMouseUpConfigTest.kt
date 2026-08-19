// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaBrowserLeftMouseUpConfigTest {

    @Test
    fun create() {
        val betaBrowserLeftMouseUpConfig =
            BetaBrowserLeftMouseUpConfig.builder().deferLoading(true).enabled(true).build()

        assertThat(betaBrowserLeftMouseUpConfig.deferLoading()).contains(true)
        assertThat(betaBrowserLeftMouseUpConfig.enabled()).contains(true)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaBrowserLeftMouseUpConfig =
            BetaBrowserLeftMouseUpConfig.builder().deferLoading(true).enabled(true).build()

        val roundtrippedBetaBrowserLeftMouseUpConfig =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaBrowserLeftMouseUpConfig),
                jacksonTypeRef<BetaBrowserLeftMouseUpConfig>(),
            )

        assertThat(roundtrippedBetaBrowserLeftMouseUpConfig).isEqualTo(betaBrowserLeftMouseUpConfig)
    }
}
