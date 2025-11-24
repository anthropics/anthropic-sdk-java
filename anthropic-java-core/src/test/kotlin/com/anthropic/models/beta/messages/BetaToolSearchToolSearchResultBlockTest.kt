// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaToolSearchToolSearchResultBlockTest {

    @Test
    fun create() {
        val betaToolSearchToolSearchResultBlock =
            BetaToolSearchToolSearchResultBlock.builder()
                .addToolReference(BetaToolReferenceBlock.builder().toolName("tool_name").build())
                .build()

        assertThat(betaToolSearchToolSearchResultBlock.toolReferences())
            .containsExactly(BetaToolReferenceBlock.builder().toolName("tool_name").build())
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaToolSearchToolSearchResultBlock =
            BetaToolSearchToolSearchResultBlock.builder()
                .addToolReference(BetaToolReferenceBlock.builder().toolName("tool_name").build())
                .build()

        val roundtrippedBetaToolSearchToolSearchResultBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaToolSearchToolSearchResultBlock),
                jacksonTypeRef<BetaToolSearchToolSearchResultBlock>(),
            )

        assertThat(roundtrippedBetaToolSearchToolSearchResultBlock)
            .isEqualTo(betaToolSearchToolSearchResultBlock)
    }
}
