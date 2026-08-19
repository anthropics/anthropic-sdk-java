// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaBrowserLeftClickDragConfigTest {

    @Test
    fun create() {
        val betaBrowserLeftClickDragConfig =
            BetaBrowserLeftClickDragConfig.builder().deferLoading(true).enabled(true).build()

        assertThat(betaBrowserLeftClickDragConfig.deferLoading()).contains(true)
        assertThat(betaBrowserLeftClickDragConfig.enabled()).contains(true)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaBrowserLeftClickDragConfig =
            BetaBrowserLeftClickDragConfig.builder().deferLoading(true).enabled(true).build()

        val roundtrippedBetaBrowserLeftClickDragConfig =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaBrowserLeftClickDragConfig),
                jacksonTypeRef<BetaBrowserLeftClickDragConfig>(),
            )

        assertThat(roundtrippedBetaBrowserLeftClickDragConfig)
            .isEqualTo(betaBrowserLeftClickDragConfig)
    }
}
