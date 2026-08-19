// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BrowserZoomConfigTest {

    @Test
    fun create() {
        val browserZoomConfig = BrowserZoomConfig.builder().deferLoading(true).enabled(true).build()

        assertThat(browserZoomConfig.deferLoading()).contains(true)
        assertThat(browserZoomConfig.enabled()).contains(true)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val browserZoomConfig = BrowserZoomConfig.builder().deferLoading(true).enabled(true).build()

        val roundtrippedBrowserZoomConfig =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(browserZoomConfig),
                jacksonTypeRef<BrowserZoomConfig>(),
            )

        assertThat(roundtrippedBrowserZoomConfig).isEqualTo(browserZoomConfig)
    }
}
