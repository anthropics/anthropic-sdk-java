// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BrowserFileUploadConfigTest {

    @Test
    fun create() {
        val browserFileUploadConfig =
            BrowserFileUploadConfig.builder().deferLoading(true).enabled(true).build()

        assertThat(browserFileUploadConfig.deferLoading()).contains(true)
        assertThat(browserFileUploadConfig.enabled()).contains(true)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val browserFileUploadConfig =
            BrowserFileUploadConfig.builder().deferLoading(true).enabled(true).build()

        val roundtrippedBrowserFileUploadConfig =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(browserFileUploadConfig),
                jacksonTypeRef<BrowserFileUploadConfig>(),
            )

        assertThat(roundtrippedBrowserFileUploadConfig).isEqualTo(browserFileUploadConfig)
    }
}
