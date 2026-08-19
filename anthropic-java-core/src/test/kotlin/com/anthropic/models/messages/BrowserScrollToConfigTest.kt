// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BrowserScrollToConfigTest {

    @Test
    fun create() {
        val browserScrollToConfig =
            BrowserScrollToConfig.builder().deferLoading(true).enabled(true).build()

        assertThat(browserScrollToConfig.deferLoading()).contains(true)
        assertThat(browserScrollToConfig.enabled()).contains(true)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val browserScrollToConfig =
            BrowserScrollToConfig.builder().deferLoading(true).enabled(true).build()

        val roundtrippedBrowserScrollToConfig =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(browserScrollToConfig),
                jacksonTypeRef<BrowserScrollToConfig>(),
            )

        assertThat(roundtrippedBrowserScrollToConfig).isEqualTo(browserScrollToConfig)
    }
}
