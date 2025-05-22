// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaRequestMcpServerUrlDefinitionTest {

    @Test
    fun create() {
        val betaRequestMcpServerUrlDefinition =
            BetaRequestMcpServerUrlDefinition.builder()
                .name("name")
                .url("url")
                .authorizationToken("authorization_token")
                .toolConfiguration(
                    BetaRequestMcpServerToolConfiguration.builder()
                        .addAllowedTool("string")
                        .enabled(true)
                        .build()
                )
                .build()

        assertThat(betaRequestMcpServerUrlDefinition.name()).isEqualTo("name")
        assertThat(betaRequestMcpServerUrlDefinition.url()).isEqualTo("url")
        assertThat(betaRequestMcpServerUrlDefinition.authorizationToken())
            .contains("authorization_token")
        assertThat(betaRequestMcpServerUrlDefinition.toolConfiguration())
            .contains(
                BetaRequestMcpServerToolConfiguration.builder()
                    .addAllowedTool("string")
                    .enabled(true)
                    .build()
            )
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaRequestMcpServerUrlDefinition =
            BetaRequestMcpServerUrlDefinition.builder()
                .name("name")
                .url("url")
                .authorizationToken("authorization_token")
                .toolConfiguration(
                    BetaRequestMcpServerToolConfiguration.builder()
                        .addAllowedTool("string")
                        .enabled(true)
                        .build()
                )
                .build()

        val roundtrippedBetaRequestMcpServerUrlDefinition =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaRequestMcpServerUrlDefinition),
                jacksonTypeRef<BetaRequestMcpServerUrlDefinition>(),
            )

        assertThat(roundtrippedBetaRequestMcpServerUrlDefinition)
            .isEqualTo(betaRequestMcpServerUrlDefinition)
    }
}
