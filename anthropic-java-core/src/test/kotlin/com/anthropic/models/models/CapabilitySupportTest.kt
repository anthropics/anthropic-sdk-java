// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.models

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class CapabilitySupportTest {

    @Test
    fun create() {
        val capabilitySupport = CapabilitySupport.of(true)

        assertThat(capabilitySupport.supported()).isEqualTo(true)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val capabilitySupport = CapabilitySupport.of(true)

        val roundtrippedCapabilitySupport =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(capabilitySupport),
                jacksonTypeRef<CapabilitySupport>(),
            )

        assertThat(roundtrippedCapabilitySupport).isEqualTo(capabilitySupport)
    }
}
