// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BrowserSwitchTabConfigTest {

    @Test
    fun create() {
        val browserSwitchTabConfig =
            BrowserSwitchTabConfig.builder().deferLoading(true).enabled(true).build()

        assertThat(browserSwitchTabConfig.deferLoading()).contains(true)
        assertThat(browserSwitchTabConfig.enabled()).contains(true)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val browserSwitchTabConfig =
            BrowserSwitchTabConfig.builder().deferLoading(true).enabled(true).build()

        val roundtrippedBrowserSwitchTabConfig =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(browserSwitchTabConfig),
                jacksonTypeRef<BrowserSwitchTabConfig>(),
            )

        assertThat(roundtrippedBrowserSwitchTabConfig).isEqualTo(browserSwitchTabConfig)
    }
}
