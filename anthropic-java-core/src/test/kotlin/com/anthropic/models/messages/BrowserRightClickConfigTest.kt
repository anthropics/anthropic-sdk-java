// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BrowserRightClickConfigTest {

    @Test
    fun create() {
        val browserRightClickConfig =
            BrowserRightClickConfig.builder().deferLoading(true).enabled(true).build()

        assertThat(browserRightClickConfig.deferLoading()).contains(true)
        assertThat(browserRightClickConfig.enabled()).contains(true)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val browserRightClickConfig =
            BrowserRightClickConfig.builder().deferLoading(true).enabled(true).build()

        val roundtrippedBrowserRightClickConfig =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(browserRightClickConfig),
                jacksonTypeRef<BrowserRightClickConfig>(),
            )

        assertThat(roundtrippedBrowserRightClickConfig).isEqualTo(browserRightClickConfig)
    }
}
