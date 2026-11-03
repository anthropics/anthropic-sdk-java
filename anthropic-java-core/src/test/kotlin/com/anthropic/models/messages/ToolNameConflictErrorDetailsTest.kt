package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ToolNameConflictErrorDetailsTest {

    @Test
    fun create() {
        val toolNameConflictErrorDetails = ToolNameConflictErrorDetails.builder().build()
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val toolNameConflictErrorDetails = ToolNameConflictErrorDetails.builder().build()

        val roundtrippedToolNameConflictErrorDetails =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(toolNameConflictErrorDetails),
                jacksonTypeRef<ToolNameConflictErrorDetails>(),
            )

        assertThat(roundtrippedToolNameConflictErrorDetails).isEqualTo(toolNameConflictErrorDetails)
    }
}
