// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import kotlinx.kmp.util.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ThinkingConfigAdaptiveTest {

    @Test
    fun create() {
        val thinkingConfigAdaptive =
            ThinkingConfigAdaptive.builder()
                .display(ThinkingConfigAdaptive.Display.SUMMARIZED)
                .build()

        assertThat(thinkingConfigAdaptive.display())
            .contains(ThinkingConfigAdaptive.Display.SUMMARIZED)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val thinkingConfigAdaptive =
            ThinkingConfigAdaptive.builder()
                .display(ThinkingConfigAdaptive.Display.SUMMARIZED)
                .build()

        val roundtrippedThinkingConfigAdaptive =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(thinkingConfigAdaptive),
                jacksonTypeRef<ThinkingConfigAdaptive>(),
            )

        assertThat(roundtrippedThinkingConfigAdaptive).isEqualTo(thinkingConfigAdaptive)
    }
}
