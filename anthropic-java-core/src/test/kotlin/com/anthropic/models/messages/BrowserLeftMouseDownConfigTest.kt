// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BrowserLeftMouseDownConfigTest {

    @Test
    fun create() {
        val browserLeftMouseDownConfig =
            BrowserLeftMouseDownConfig.builder().deferLoading(true).enabled(true).build()

        assertThat(browserLeftMouseDownConfig.deferLoading()).contains(true)
        assertThat(browserLeftMouseDownConfig.enabled()).contains(true)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val browserLeftMouseDownConfig =
            BrowserLeftMouseDownConfig.builder().deferLoading(true).enabled(true).build()

        val roundtrippedBrowserLeftMouseDownConfig =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(browserLeftMouseDownConfig),
                jacksonTypeRef<BrowserLeftMouseDownConfig>(),
            )

        assertThat(roundtrippedBrowserLeftMouseDownConfig).isEqualTo(browserLeftMouseDownConfig)
    }
}
