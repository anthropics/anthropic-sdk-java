// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BrowserLeftClickConfigTest {

    @Test
    fun create() {
        val browserLeftClickConfig =
            BrowserLeftClickConfig.builder().deferLoading(true).enabled(true).build()

        assertThat(browserLeftClickConfig.deferLoading()).contains(true)
        assertThat(browserLeftClickConfig.enabled()).contains(true)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val browserLeftClickConfig =
            BrowserLeftClickConfig.builder().deferLoading(true).enabled(true).build()

        val roundtrippedBrowserLeftClickConfig =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(browserLeftClickConfig),
                jacksonTypeRef<BrowserLeftClickConfig>(),
            )

        assertThat(roundtrippedBrowserLeftClickConfig).isEqualTo(browserLeftClickConfig)
    }
}
