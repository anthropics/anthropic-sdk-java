// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BrowserCloseTabConfigTest {

    @Test
    fun create() {
        val browserCloseTabConfig =
            BrowserCloseTabConfig.builder().deferLoading(true).enabled(true).build()

        assertThat(browserCloseTabConfig.deferLoading()).contains(true)
        assertThat(browserCloseTabConfig.enabled()).contains(true)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val browserCloseTabConfig =
            BrowserCloseTabConfig.builder().deferLoading(true).enabled(true).build()

        val roundtrippedBrowserCloseTabConfig =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(browserCloseTabConfig),
                jacksonTypeRef<BrowserCloseTabConfig>(),
            )

        assertThat(roundtrippedBrowserCloseTabConfig).isEqualTo(browserCloseTabConfig)
    }
}
