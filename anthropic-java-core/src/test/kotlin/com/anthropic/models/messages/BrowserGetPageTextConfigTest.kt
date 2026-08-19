// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BrowserGetPageTextConfigTest {

    @Test
    fun create() {
        val browserGetPageTextConfig =
            BrowserGetPageTextConfig.builder().deferLoading(true).enabled(true).build()

        assertThat(browserGetPageTextConfig.deferLoading()).contains(true)
        assertThat(browserGetPageTextConfig.enabled()).contains(true)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val browserGetPageTextConfig =
            BrowserGetPageTextConfig.builder().deferLoading(true).enabled(true).build()

        val roundtrippedBrowserGetPageTextConfig =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(browserGetPageTextConfig),
                jacksonTypeRef<BrowserGetPageTextConfig>(),
            )

        assertThat(roundtrippedBrowserGetPageTextConfig).isEqualTo(browserGetPageTextConfig)
    }
}
