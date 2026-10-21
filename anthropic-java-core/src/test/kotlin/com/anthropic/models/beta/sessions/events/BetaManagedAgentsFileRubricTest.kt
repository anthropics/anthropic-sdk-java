package com.anthropic.models.beta.sessions.events

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsFileRubricTest {

    @Test
    fun create() {
        val betaManagedAgentsFileRubric = BetaManagedAgentsFileRubric.of("file_id")

        assertThat(betaManagedAgentsFileRubric.fileId()).isEqualTo("file_id")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsFileRubric = BetaManagedAgentsFileRubric.of("file_id")

        val roundtrippedBetaManagedAgentsFileRubric =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsFileRubric),
                jacksonTypeRef<BetaManagedAgentsFileRubric>(),
            )

        assertThat(roundtrippedBetaManagedAgentsFileRubric).isEqualTo(betaManagedAgentsFileRubric)
    }
}
