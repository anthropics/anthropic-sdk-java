// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaBrowserRightClickConfigTest {

    @Test
    fun create() {
        val betaBrowserRightClickConfig =
            BetaBrowserRightClickConfig.builder().deferLoading(true).enabled(true).build()

        assertThat(betaBrowserRightClickConfig.deferLoading()).contains(true)
        assertThat(betaBrowserRightClickConfig.enabled()).contains(true)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaBrowserRightClickConfig =
            BetaBrowserRightClickConfig.builder().deferLoading(true).enabled(true).build()

        val roundtrippedBetaBrowserRightClickConfig =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaBrowserRightClickConfig),
                jacksonTypeRef<BetaBrowserRightClickConfig>(),
            )

        assertThat(roundtrippedBetaBrowserRightClickConfig).isEqualTo(betaBrowserRightClickConfig)
    }
}
