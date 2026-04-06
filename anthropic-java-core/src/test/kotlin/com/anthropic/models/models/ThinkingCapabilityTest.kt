// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.models

import kotlinx.kmp.util.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ThinkingCapabilityTest {

    @Test
    fun create() {
        val thinkingCapability =
            ThinkingCapability.builder()
                .supported(true)
                .types(
                    ThinkingTypes.builder()
                        .adaptive(CapabilitySupport.builder().supported(true).build())
                        .enabled(CapabilitySupport.builder().supported(true).build())
                        .build()
                )
                .build()

        assertThat(thinkingCapability.supported()).isEqualTo(true)
        assertThat(thinkingCapability.types())
            .isEqualTo(
                ThinkingTypes.builder()
                    .adaptive(CapabilitySupport.builder().supported(true).build())
                    .enabled(CapabilitySupport.builder().supported(true).build())
                    .build()
            )
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val thinkingCapability =
            ThinkingCapability.builder()
                .supported(true)
                .types(
                    ThinkingTypes.builder()
                        .adaptive(CapabilitySupport.builder().supported(true).build())
                        .enabled(CapabilitySupport.builder().supported(true).build())
                        .build()
                )
                .build()

        val roundtrippedThinkingCapability =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(thinkingCapability),
                jacksonTypeRef<ThinkingCapability>(),
            )

        assertThat(roundtrippedThinkingCapability).isEqualTo(thinkingCapability)
    }
}
