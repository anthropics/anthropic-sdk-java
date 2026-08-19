// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BrowserFindConfigTest {

    @Test
    fun create() {
        val browserFindConfig = BrowserFindConfig.builder().deferLoading(true).enabled(true).build()

        assertThat(browserFindConfig.deferLoading()).contains(true)
        assertThat(browserFindConfig.enabled()).contains(true)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val browserFindConfig = BrowserFindConfig.builder().deferLoading(true).enabled(true).build()

        val roundtrippedBrowserFindConfig =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(browserFindConfig),
                jacksonTypeRef<BrowserFindConfig>(),
            )

        assertThat(roundtrippedBrowserFindConfig).isEqualTo(browserFindConfig)
    }
}
