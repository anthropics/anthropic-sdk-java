// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.models

import kotlinx.kmp.util.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class CapabilitySupportTest {

    @Test
    fun create() {
        val capabilitySupport = CapabilitySupport.builder().supported(true).build()

        assertThat(capabilitySupport.supported()).isEqualTo(true)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val capabilitySupport = CapabilitySupport.builder().supported(true).build()

        val roundtrippedCapabilitySupport =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(capabilitySupport),
                jacksonTypeRef<CapabilitySupport>(),
            )

        assertThat(roundtrippedCapabilitySupport).isEqualTo(capabilitySupport)
    }
}
