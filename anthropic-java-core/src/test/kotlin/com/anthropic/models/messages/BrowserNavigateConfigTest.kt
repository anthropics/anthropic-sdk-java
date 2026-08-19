// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BrowserNavigateConfigTest {

    @Test
    fun create() {
        val browserNavigateConfig =
            BrowserNavigateConfig.builder().deferLoading(true).enabled(true).build()

        assertThat(browserNavigateConfig.deferLoading()).contains(true)
        assertThat(browserNavigateConfig.enabled()).contains(true)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val browserNavigateConfig =
            BrowserNavigateConfig.builder().deferLoading(true).enabled(true).build()

        val roundtrippedBrowserNavigateConfig =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(browserNavigateConfig),
                jacksonTypeRef<BrowserNavigateConfig>(),
            )

        assertThat(roundtrippedBrowserNavigateConfig).isEqualTo(browserNavigateConfig)
    }
}
