// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BrowserReadConsoleConfigTest {

    @Test
    fun create() {
        val browserReadConsoleConfig =
            BrowserReadConsoleConfig.builder().deferLoading(true).enabled(true).build()

        assertThat(browserReadConsoleConfig.deferLoading()).contains(true)
        assertThat(browserReadConsoleConfig.enabled()).contains(true)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val browserReadConsoleConfig =
            BrowserReadConsoleConfig.builder().deferLoading(true).enabled(true).build()

        val roundtrippedBrowserReadConsoleConfig =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(browserReadConsoleConfig),
                jacksonTypeRef<BrowserReadConsoleConfig>(),
            )

        assertThat(roundtrippedBrowserReadConsoleConfig).isEqualTo(browserReadConsoleConfig)
    }
}
