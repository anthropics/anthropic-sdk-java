// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BrowserLeftMouseUpConfigTest {

    @Test
    fun create() {
        val browserLeftMouseUpConfig =
            BrowserLeftMouseUpConfig.builder().deferLoading(true).enabled(true).build()

        assertThat(browserLeftMouseUpConfig.deferLoading()).contains(true)
        assertThat(browserLeftMouseUpConfig.enabled()).contains(true)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val browserLeftMouseUpConfig =
            BrowserLeftMouseUpConfig.builder().deferLoading(true).enabled(true).build()

        val roundtrippedBrowserLeftMouseUpConfig =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(browserLeftMouseUpConfig),
                jacksonTypeRef<BrowserLeftMouseUpConfig>(),
            )

        assertThat(roundtrippedBrowserLeftMouseUpConfig).isEqualTo(browserLeftMouseUpConfig)
    }
}
