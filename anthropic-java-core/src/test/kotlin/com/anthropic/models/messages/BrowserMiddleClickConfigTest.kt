// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BrowserMiddleClickConfigTest {

    @Test
    fun create() {
        val browserMiddleClickConfig =
            BrowserMiddleClickConfig.builder().deferLoading(true).enabled(true).build()

        assertThat(browserMiddleClickConfig.deferLoading()).contains(true)
        assertThat(browserMiddleClickConfig.enabled()).contains(true)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val browserMiddleClickConfig =
            BrowserMiddleClickConfig.builder().deferLoading(true).enabled(true).build()

        val roundtrippedBrowserMiddleClickConfig =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(browserMiddleClickConfig),
                jacksonTypeRef<BrowserMiddleClickConfig>(),
            )

        assertThat(roundtrippedBrowserMiddleClickConfig).isEqualTo(browserMiddleClickConfig)
    }
}
