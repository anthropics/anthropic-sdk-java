// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.models

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ThinkingTypesTest {

    @Test
    fun create() {
        val thinkingTypes =
            ThinkingTypes.builder()
                .adaptive(CapabilitySupport.of(true))
                .enabled(CapabilitySupport.of(true))
                .build()

        assertThat(thinkingTypes.adaptive()).isEqualTo(CapabilitySupport.of(true))
        assertThat(thinkingTypes.enabled()).isEqualTo(CapabilitySupport.of(true))
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val thinkingTypes =
            ThinkingTypes.builder()
                .adaptive(CapabilitySupport.of(true))
                .enabled(CapabilitySupport.of(true))
                .build()

        val roundtrippedThinkingTypes =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(thinkingTypes),
                jacksonTypeRef<ThinkingTypes>(),
            )

        assertThat(roundtrippedThinkingTypes).isEqualTo(thinkingTypes)
    }
}
