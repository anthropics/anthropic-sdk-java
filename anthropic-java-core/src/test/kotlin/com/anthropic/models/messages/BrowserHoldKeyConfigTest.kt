// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BrowserHoldKeyConfigTest {

    @Test
    fun create() {
        val browserHoldKeyConfig =
            BrowserHoldKeyConfig.builder().deferLoading(true).enabled(true).build()

        assertThat(browserHoldKeyConfig.deferLoading()).contains(true)
        assertThat(browserHoldKeyConfig.enabled()).contains(true)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val browserHoldKeyConfig =
            BrowserHoldKeyConfig.builder().deferLoading(true).enabled(true).build()

        val roundtrippedBrowserHoldKeyConfig =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(browserHoldKeyConfig),
                jacksonTypeRef<BrowserHoldKeyConfig>(),
            )

        assertThat(roundtrippedBrowserHoldKeyConfig).isEqualTo(browserHoldKeyConfig)
    }
}
