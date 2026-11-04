package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ToolReferenceUnresolvedErrorDetailsTest {

    @Test
    fun create() {
        val toolReferenceUnresolvedErrorDetails =
            ToolReferenceUnresolvedErrorDetails.builder().build()
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val toolReferenceUnresolvedErrorDetails =
            ToolReferenceUnresolvedErrorDetails.builder().build()

        val roundtrippedToolReferenceUnresolvedErrorDetails =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(toolReferenceUnresolvedErrorDetails),
                jacksonTypeRef<ToolReferenceUnresolvedErrorDetails>(),
            )

        assertThat(roundtrippedToolReferenceUnresolvedErrorDetails)
            .isEqualTo(toolReferenceUnresolvedErrorDetails)
    }
}
