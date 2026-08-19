// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaBrowserReadConsoleConfigTest {

    @Test
    fun create() {
        val betaBrowserReadConsoleConfig =
            BetaBrowserReadConsoleConfig.builder().deferLoading(true).enabled(true).build()

        assertThat(betaBrowserReadConsoleConfig.deferLoading()).contains(true)
        assertThat(betaBrowserReadConsoleConfig.enabled()).contains(true)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaBrowserReadConsoleConfig =
            BetaBrowserReadConsoleConfig.builder().deferLoading(true).enabled(true).build()

        val roundtrippedBetaBrowserReadConsoleConfig =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaBrowserReadConsoleConfig),
                jacksonTypeRef<BetaBrowserReadConsoleConfig>(),
            )

        assertThat(roundtrippedBetaBrowserReadConsoleConfig).isEqualTo(betaBrowserReadConsoleConfig)
    }
}
