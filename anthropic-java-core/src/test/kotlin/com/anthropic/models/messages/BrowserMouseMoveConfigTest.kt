// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BrowserMouseMoveConfigTest {

    @Test
    fun create() {
        val browserMouseMoveConfig =
            BrowserMouseMoveConfig.builder().deferLoading(true).enabled(true).build()

        assertThat(browserMouseMoveConfig.deferLoading()).contains(true)
        assertThat(browserMouseMoveConfig.enabled()).contains(true)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val browserMouseMoveConfig =
            BrowserMouseMoveConfig.builder().deferLoading(true).enabled(true).build()

        val roundtrippedBrowserMouseMoveConfig =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(browserMouseMoveConfig),
                jacksonTypeRef<BrowserMouseMoveConfig>(),
            )

        assertThat(roundtrippedBrowserMouseMoveConfig).isEqualTo(browserMouseMoveConfig)
    }
}
