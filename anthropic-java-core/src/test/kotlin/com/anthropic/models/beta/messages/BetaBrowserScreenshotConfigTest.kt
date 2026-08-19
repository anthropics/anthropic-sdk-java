// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaBrowserScreenshotConfigTest {

    @Test
    fun create() {
        val betaBrowserScreenshotConfig =
            BetaBrowserScreenshotConfig.builder().deferLoading(true).enabled(true).build()

        assertThat(betaBrowserScreenshotConfig.deferLoading()).contains(true)
        assertThat(betaBrowserScreenshotConfig.enabled()).contains(true)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaBrowserScreenshotConfig =
            BetaBrowserScreenshotConfig.builder().deferLoading(true).enabled(true).build()

        val roundtrippedBetaBrowserScreenshotConfig =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaBrowserScreenshotConfig),
                jacksonTypeRef<BetaBrowserScreenshotConfig>(),
            )

        assertThat(roundtrippedBetaBrowserScreenshotConfig).isEqualTo(betaBrowserScreenshotConfig)
    }
}
