// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BrowserReadPageConfigTest {

    @Test
    fun create() {
        val browserReadPageConfig =
            BrowserReadPageConfig.builder().deferLoading(true).enabled(true).build()

        assertThat(browserReadPageConfig.deferLoading()).contains(true)
        assertThat(browserReadPageConfig.enabled()).contains(true)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val browserReadPageConfig =
            BrowserReadPageConfig.builder().deferLoading(true).enabled(true).build()

        val roundtrippedBrowserReadPageConfig =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(browserReadPageConfig),
                jacksonTypeRef<BrowserReadPageConfig>(),
            )

        assertThat(roundtrippedBrowserReadPageConfig).isEqualTo(browserReadPageConfig)
    }
}
