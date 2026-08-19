// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BrowserListTabsConfigTest {

    @Test
    fun create() {
        val browserListTabsConfig =
            BrowserListTabsConfig.builder().deferLoading(true).enabled(true).build()

        assertThat(browserListTabsConfig.deferLoading()).contains(true)
        assertThat(browserListTabsConfig.enabled()).contains(true)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val browserListTabsConfig =
            BrowserListTabsConfig.builder().deferLoading(true).enabled(true).build()

        val roundtrippedBrowserListTabsConfig =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(browserListTabsConfig),
                jacksonTypeRef<BrowserListTabsConfig>(),
            )

        assertThat(roundtrippedBrowserListTabsConfig).isEqualTo(browserListTabsConfig)
    }
}
