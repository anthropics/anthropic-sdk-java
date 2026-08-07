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
                .high(CapabilitySupport.of(true))
                .low(CapabilitySupport.of(true))
                .max(CapabilitySupport.of(true))
                .medium(CapabilitySupport.of(true))
                .supported(true)
                .xhigh(CapabilitySupport.of(true))
                .build()

        assertThat(effortCapability.high()).isEqualTo(CapabilitySupport.of(true))
        assertThat(effortCapability.low()).isEqualTo(CapabilitySupport.of(true))
        assertThat(effortCapability.max()).isEqualTo(CapabilitySupport.of(true))
        assertThat(effortCapability.medium()).isEqualTo(CapabilitySupport.of(true))
        assertThat(effortCapability.supported()).isEqualTo(true)
        assertThat(effortCapability.xhigh()).contains(CapabilitySupport.of(true))
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val effortCapability =
            EffortCapability.builder()
                .high(CapabilitySupport.of(true))
                .low(CapabilitySupport.of(true))
                .max(CapabilitySupport.of(true))
                .medium(CapabilitySupport.of(true))
                .supported(true)
                .xhigh(CapabilitySupport.of(true))
                .build()

        val roundtrippedEffortCapability =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(effortCapability),
                jacksonTypeRef<EffortCapability>(),
            )

        assertThat(roundtrippedEffortCapability).isEqualTo(effortCapability)
    }
}
