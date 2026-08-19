// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BrowserFormInputConfigTest {

    @Test
    fun create() {
        val browserFormInputConfig =
            BrowserFormInputConfig.builder().deferLoading(true).enabled(true).build()

        assertThat(browserFormInputConfig.deferLoading()).contains(true)
        assertThat(browserFormInputConfig.enabled()).contains(true)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val browserFormInputConfig =
            BrowserFormInputConfig.builder().deferLoading(true).enabled(true).build()

        val roundtrippedBrowserFormInputConfig =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(browserFormInputConfig),
                jacksonTypeRef<BrowserFormInputConfig>(),
            )

        assertThat(roundtrippedBrowserFormInputConfig).isEqualTo(browserFormInputConfig)
    }
}
