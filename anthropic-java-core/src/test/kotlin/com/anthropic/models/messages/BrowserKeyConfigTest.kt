// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BrowserKeyConfigTest {

    @Test
    fun create() {
        val browserKeyConfig = BrowserKeyConfig.builder().deferLoading(true).enabled(true).build()

        assertThat(browserKeyConfig.deferLoading()).contains(true)
        assertThat(browserKeyConfig.enabled()).contains(true)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val browserKeyConfig = BrowserKeyConfig.builder().deferLoading(true).enabled(true).build()

        val roundtrippedBrowserKeyConfig =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(browserKeyConfig),
                jacksonTypeRef<BrowserKeyConfig>(),
            )

        assertThat(roundtrippedBrowserKeyConfig).isEqualTo(browserKeyConfig)
    }
}
