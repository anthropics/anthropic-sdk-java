// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BrowserTripleClickConfigTest {

    @Test
    fun create() {
        val browserTripleClickConfig =
            BrowserTripleClickConfig.builder().deferLoading(true).enabled(true).build()

        assertThat(browserTripleClickConfig.deferLoading()).contains(true)
        assertThat(browserTripleClickConfig.enabled()).contains(true)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val browserTripleClickConfig =
            BrowserTripleClickConfig.builder().deferLoading(true).enabled(true).build()

        val roundtrippedBrowserTripleClickConfig =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(browserTripleClickConfig),
                jacksonTypeRef<BrowserTripleClickConfig>(),
            )

        assertThat(roundtrippedBrowserTripleClickConfig).isEqualTo(browserTripleClickConfig)
    }
}
