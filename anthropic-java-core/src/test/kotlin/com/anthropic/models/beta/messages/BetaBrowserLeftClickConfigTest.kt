// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaBrowserLeftClickConfigTest {

    @Test
    fun create() {
        val betaBrowserLeftClickConfig =
            BetaBrowserLeftClickConfig.builder().deferLoading(true).enabled(true).build()

        assertThat(betaBrowserLeftClickConfig.deferLoading()).contains(true)
        assertThat(betaBrowserLeftClickConfig.enabled()).contains(true)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaBrowserLeftClickConfig =
            BetaBrowserLeftClickConfig.builder().deferLoading(true).enabled(true).build()

        val roundtrippedBetaBrowserLeftClickConfig =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaBrowserLeftClickConfig),
                jacksonTypeRef<BetaBrowserLeftClickConfig>(),
            )

        assertThat(roundtrippedBetaBrowserLeftClickConfig).isEqualTo(betaBrowserLeftClickConfig)
    }
}
