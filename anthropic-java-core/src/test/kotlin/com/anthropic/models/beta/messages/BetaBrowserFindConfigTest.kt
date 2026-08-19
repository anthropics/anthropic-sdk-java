// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaBrowserFindConfigTest {

    @Test
    fun create() {
        val betaBrowserFindConfig =
            BetaBrowserFindConfig.builder().deferLoading(true).enabled(true).build()

        assertThat(betaBrowserFindConfig.deferLoading()).contains(true)
        assertThat(betaBrowserFindConfig.enabled()).contains(true)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaBrowserFindConfig =
            BetaBrowserFindConfig.builder().deferLoading(true).enabled(true).build()

        val roundtrippedBetaBrowserFindConfig =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaBrowserFindConfig),
                jacksonTypeRef<BetaBrowserFindConfig>(),
            )

        assertThat(roundtrippedBetaBrowserFindConfig).isEqualTo(betaBrowserFindConfig)
    }
}
