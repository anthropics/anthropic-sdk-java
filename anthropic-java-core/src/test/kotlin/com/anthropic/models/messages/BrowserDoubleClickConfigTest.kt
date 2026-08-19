// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BrowserDoubleClickConfigTest {

    @Test
    fun create() {
        val browserDoubleClickConfig =
            BrowserDoubleClickConfig.builder().deferLoading(true).enabled(true).build()

        assertThat(browserDoubleClickConfig.deferLoading()).contains(true)
        assertThat(browserDoubleClickConfig.enabled()).contains(true)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val browserDoubleClickConfig =
            BrowserDoubleClickConfig.builder().deferLoading(true).enabled(true).build()

        val roundtrippedBrowserDoubleClickConfig =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(browserDoubleClickConfig),
                jacksonTypeRef<BrowserDoubleClickConfig>(),
            )

        assertThat(roundtrippedBrowserDoubleClickConfig).isEqualTo(browserDoubleClickConfig)
    }
}
