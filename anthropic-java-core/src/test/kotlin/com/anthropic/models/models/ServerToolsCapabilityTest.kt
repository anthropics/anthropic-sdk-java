package com.anthropic.models.models

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ServerToolsCapabilityTest {

    @Test
    fun create() {
        val serverToolsCapability =
            ServerToolsCapability.builder()
                .codeExecution(CapabilitySupport.of(true))
                .supported(true)
                .webSearch(CapabilitySupport.of(true))
                .build()

        assertThat(serverToolsCapability.codeExecution()).isEqualTo(CapabilitySupport.of(true))
        assertThat(serverToolsCapability.supported()).isEqualTo(true)
        assertThat(serverToolsCapability.webSearch()).isEqualTo(CapabilitySupport.of(true))
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val serverToolsCapability =
            ServerToolsCapability.builder()
                .codeExecution(CapabilitySupport.of(true))
                .supported(true)
                .webSearch(CapabilitySupport.of(true))
                .build()

        val roundtrippedServerToolsCapability =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(serverToolsCapability),
                jacksonTypeRef<ServerToolsCapability>(),
            )

        assertThat(roundtrippedServerToolsCapability).isEqualTo(serverToolsCapability)
    }
}
