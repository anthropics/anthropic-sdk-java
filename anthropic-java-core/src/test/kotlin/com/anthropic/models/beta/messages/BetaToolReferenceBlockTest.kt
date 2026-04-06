// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import kotlinx.kmp.util.core.jsonMapper
import kotlinx.kmp.util.core.json.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaToolReferenceBlockTest {

    @Test
    fun create() {
        val betaToolReferenceBlock = BetaToolReferenceBlock.builder().toolName("tool_name").build()

        assertThat(betaToolReferenceBlock.toolName()).isEqualTo("tool_name")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaToolReferenceBlock = BetaToolReferenceBlock.builder().toolName("tool_name").build()

        val roundtrippedBetaToolReferenceBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaToolReferenceBlock),
                jacksonTypeRef<BetaToolReferenceBlock>(),
            )

        assertThat(roundtrippedBetaToolReferenceBlock).isEqualTo(betaToolReferenceBlock)
    }
}
