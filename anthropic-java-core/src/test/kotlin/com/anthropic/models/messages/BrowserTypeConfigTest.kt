// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BrowserTypeConfigTest {

    @Test
    fun create() {
        val browserTypeConfig = BrowserTypeConfig.builder().deferLoading(true).enabled(true).build()

        assertThat(browserTypeConfig.deferLoading()).contains(true)
        assertThat(browserTypeConfig.enabled()).contains(true)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val browserTypeConfig = BrowserTypeConfig.builder().deferLoading(true).enabled(true).build()

        val roundtrippedBrowserTypeConfig =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(browserTypeConfig),
                jacksonTypeRef<BrowserTypeConfig>(),
            )

        assertThat(roundtrippedBrowserTypeConfig).isEqualTo(browserTypeConfig)
    }
}
