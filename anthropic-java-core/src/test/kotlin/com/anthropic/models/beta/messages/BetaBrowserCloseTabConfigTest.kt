// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaBrowserCloseTabConfigTest {

    @Test
    fun create() {
        val betaBrowserCloseTabConfig =
            BetaBrowserCloseTabConfig.builder().deferLoading(true).enabled(true).build()

        assertThat(betaBrowserCloseTabConfig.deferLoading()).contains(true)
        assertThat(betaBrowserCloseTabConfig.enabled()).contains(true)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaBrowserCloseTabConfig =
            BetaBrowserCloseTabConfig.builder().deferLoading(true).enabled(true).build()

        val roundtrippedBetaBrowserCloseTabConfig =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaBrowserCloseTabConfig),
                jacksonTypeRef<BetaBrowserCloseTabConfig>(),
            )

        assertThat(roundtrippedBetaBrowserCloseTabConfig).isEqualTo(betaBrowserCloseTabConfig)
    }
}
