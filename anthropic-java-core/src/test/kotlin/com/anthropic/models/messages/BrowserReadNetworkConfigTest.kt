// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BrowserReadNetworkConfigTest {

    @Test
    fun create() {
        val browserReadNetworkConfig =
            BrowserReadNetworkConfig.builder().deferLoading(true).enabled(true).build()

        assertThat(browserReadNetworkConfig.deferLoading()).contains(true)
        assertThat(browserReadNetworkConfig.enabled()).contains(true)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val browserReadNetworkConfig =
            BrowserReadNetworkConfig.builder().deferLoading(true).enabled(true).build()

        val roundtrippedBrowserReadNetworkConfig =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(browserReadNetworkConfig),
                jacksonTypeRef<BrowserReadNetworkConfig>(),
            )

        assertThat(roundtrippedBrowserReadNetworkConfig).isEqualTo(browserReadNetworkConfig)
    }
}
