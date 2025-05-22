// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import kotlin.jvm.optionals.getOrNull
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaRequestMcpServerToolConfigurationTest {

    @Test
    fun create() {
        val betaRequestMcpServerToolConfiguration =
            BetaRequestMcpServerToolConfiguration.builder()
                .addAllowedTool("string")
                .enabled(true)
                .build()

        assertThat(betaRequestMcpServerToolConfiguration.allowedTools().getOrNull())
            .containsExactly("string")
        assertThat(betaRequestMcpServerToolConfiguration.enabled()).contains(true)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaRequestMcpServerToolConfiguration =
            BetaRequestMcpServerToolConfiguration.builder()
                .addAllowedTool("string")
                .enabled(true)
                .build()

        val roundtrippedBetaRequestMcpServerToolConfiguration =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaRequestMcpServerToolConfiguration),
                jacksonTypeRef<BetaRequestMcpServerToolConfiguration>(),
            )

        assertThat(roundtrippedBetaRequestMcpServerToolConfiguration)
            .isEqualTo(betaRequestMcpServerToolConfiguration)
    }
}
