// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BrowserScreenshotConfigTest {

    @Test
    fun create() {
        val browserScreenshotConfig =
            BrowserScreenshotConfig.builder().deferLoading(true).enabled(true).build()

        assertThat(browserScreenshotConfig.deferLoading()).contains(true)
        assertThat(browserScreenshotConfig.enabled()).contains(true)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val browserScreenshotConfig =
            BrowserScreenshotConfig.builder().deferLoading(true).enabled(true).build()

        val roundtrippedBrowserScreenshotConfig =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(browserScreenshotConfig),
                jacksonTypeRef<BrowserScreenshotConfig>(),
            )

        assertThat(roundtrippedBrowserScreenshotConfig).isEqualTo(browserScreenshotConfig)
    }
}
