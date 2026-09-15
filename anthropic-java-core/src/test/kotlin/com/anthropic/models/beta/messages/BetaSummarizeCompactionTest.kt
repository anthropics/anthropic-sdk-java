package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaSummarizeCompactionTest {

    @Test
    fun create() {
        val betaSummarizeCompaction =
            BetaSummarizeCompaction.builder().instructions("instructions").build()

        assertThat(betaSummarizeCompaction.instructions()).contains("instructions")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaSummarizeCompaction =
            BetaSummarizeCompaction.builder().instructions("instructions").build()

        val roundtrippedBetaSummarizeCompaction =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaSummarizeCompaction),
                jacksonTypeRef<BetaSummarizeCompaction>(),
            )

        assertThat(roundtrippedBetaSummarizeCompaction).isEqualTo(betaSummarizeCompaction)
    }
}
