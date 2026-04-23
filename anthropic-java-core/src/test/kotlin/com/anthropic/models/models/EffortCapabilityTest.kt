// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.models

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class EffortCapabilityTest {

    @Test
    fun create() {
        val effortCapability =
            EffortCapability.builder()
                .high(CapabilitySupport.builder().supported(true).build())
                .low(CapabilitySupport.builder().supported(true).build())
                .max(CapabilitySupport.builder().supported(true).build())
                .medium(CapabilitySupport.builder().supported(true).build())
                .supported(true)
                .build()

        assertThat(effortCapability.high())
            .isEqualTo(CapabilitySupport.builder().supported(true).build())
        assertThat(effortCapability.low())
            .isEqualTo(CapabilitySupport.builder().supported(true).build())
        assertThat(effortCapability.max())
            .isEqualTo(CapabilitySupport.builder().supported(true).build())
        assertThat(effortCapability.medium())
            .isEqualTo(CapabilitySupport.builder().supported(true).build())
        assertThat(effortCapability.supported()).isEqualTo(true)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val effortCapability =
            EffortCapability.builder()
                .high(CapabilitySupport.builder().supported(true).build())
                .low(CapabilitySupport.builder().supported(true).build())
                .max(CapabilitySupport.builder().supported(true).build())
                .medium(CapabilitySupport.builder().supported(true).build())
                .supported(true)
                .build()

        val roundtrippedEffortCapability =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(effortCapability),
                jacksonTypeRef<EffortCapability>(),
            )

        assertThat(roundtrippedEffortCapability).isEqualTo(effortCapability)
    }
}
