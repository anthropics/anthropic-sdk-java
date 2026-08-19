// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BrowserLeftClickDragConfigTest {

    @Test
    fun create() {
        val browserLeftClickDragConfig =
            BrowserLeftClickDragConfig.builder().deferLoading(true).enabled(true).build()

        assertThat(browserLeftClickDragConfig.deferLoading()).contains(true)
        assertThat(browserLeftClickDragConfig.enabled()).contains(true)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val browserLeftClickDragConfig =
            BrowserLeftClickDragConfig.builder().deferLoading(true).enabled(true).build()

        val roundtrippedBrowserLeftClickDragConfig =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(browserLeftClickDragConfig),
                jacksonTypeRef<BrowserLeftClickDragConfig>(),
            )

        assertThat(roundtrippedBrowserLeftClickDragConfig).isEqualTo(browserLeftClickDragConfig)
    }
}
