// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BrowserHoverConfigTest {

    @Test
    fun create() {
        val browserHoverConfig =
            BrowserHoverConfig.builder().deferLoading(true).enabled(true).build()

        assertThat(browserHoverConfig.deferLoading()).contains(true)
        assertThat(browserHoverConfig.enabled()).contains(true)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val browserHoverConfig =
            BrowserHoverConfig.builder().deferLoading(true).enabled(true).build()

        val roundtrippedBrowserHoverConfig =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(browserHoverConfig),
                jacksonTypeRef<BrowserHoverConfig>(),
            )

        assertThat(roundtrippedBrowserHoverConfig).isEqualTo(browserHoverConfig)
    }
}
