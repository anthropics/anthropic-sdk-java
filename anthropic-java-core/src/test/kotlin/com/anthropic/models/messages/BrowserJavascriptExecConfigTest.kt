// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BrowserJavascriptExecConfigTest {

    @Test
    fun create() {
        val browserJavascriptExecConfig =
            BrowserJavascriptExecConfig.builder().deferLoading(true).enabled(true).build()

        assertThat(browserJavascriptExecConfig.deferLoading()).contains(true)
        assertThat(browserJavascriptExecConfig.enabled()).contains(true)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val browserJavascriptExecConfig =
            BrowserJavascriptExecConfig.builder().deferLoading(true).enabled(true).build()

        val roundtrippedBrowserJavascriptExecConfig =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(browserJavascriptExecConfig),
                jacksonTypeRef<BrowserJavascriptExecConfig>(),
            )

        assertThat(roundtrippedBrowserJavascriptExecConfig).isEqualTo(browserJavascriptExecConfig)
    }
}
