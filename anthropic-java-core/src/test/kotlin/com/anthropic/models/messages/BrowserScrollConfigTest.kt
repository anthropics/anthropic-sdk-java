// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BrowserScrollConfigTest {

    @Test
    fun create() {
        val browserScrollConfig =
            BrowserScrollConfig.builder().deferLoading(true).enabled(true).build()

        assertThat(browserScrollConfig.deferLoading()).contains(true)
        assertThat(browserScrollConfig.enabled()).contains(true)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val browserScrollConfig =
            BrowserScrollConfig.builder().deferLoading(true).enabled(true).build()

        val roundtrippedBrowserScrollConfig =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(browserScrollConfig),
                jacksonTypeRef<BrowserScrollConfig>(),
            )

        assertThat(roundtrippedBrowserScrollConfig).isEqualTo(browserScrollConfig)
    }
}
