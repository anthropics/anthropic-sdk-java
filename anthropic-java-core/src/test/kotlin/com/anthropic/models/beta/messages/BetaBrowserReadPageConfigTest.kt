// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaBrowserReadPageConfigTest {

    @Test
    fun create() {
        val betaBrowserReadPageConfig =
            BetaBrowserReadPageConfig.builder().deferLoading(true).enabled(true).build()

        assertThat(betaBrowserReadPageConfig.deferLoading()).contains(true)
        assertThat(betaBrowserReadPageConfig.enabled()).contains(true)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaBrowserReadPageConfig =
            BetaBrowserReadPageConfig.builder().deferLoading(true).enabled(true).build()

        val roundtrippedBetaBrowserReadPageConfig =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaBrowserReadPageConfig),
                jacksonTypeRef<BetaBrowserReadPageConfig>(),
            )

        assertThat(roundtrippedBetaBrowserReadPageConfig).isEqualTo(betaBrowserReadPageConfig)
    }
}
