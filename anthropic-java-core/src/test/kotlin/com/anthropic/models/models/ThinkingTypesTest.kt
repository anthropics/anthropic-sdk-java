// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.models

import kotlinx.kmp.util.core.jsonMapper
import kotlinx.kmp.util.core.json.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ThinkingTypesTest {

    @Test
    fun create() {
        val thinkingTypes =
            ThinkingTypes.builder()
                .adaptive(CapabilitySupport.builder().supported(true).build())
                .enabled(CapabilitySupport.builder().supported(true).build())
                .build()

        assertThat(thinkingTypes.adaptive())
            .isEqualTo(CapabilitySupport.builder().supported(true).build())
        assertThat(thinkingTypes.enabled())
            .isEqualTo(CapabilitySupport.builder().supported(true).build())
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val thinkingTypes =
            ThinkingTypes.builder()
                .adaptive(CapabilitySupport.builder().supported(true).build())
                .enabled(CapabilitySupport.builder().supported(true).build())
                .build()

        val roundtrippedThinkingTypes =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(thinkingTypes),
                jacksonTypeRef<ThinkingTypes>(),
            )

        assertThat(roundtrippedThinkingTypes).isEqualTo(thinkingTypes)
    }
}
