// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.sessions.events

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsFileRubricTest {

    @Test
    fun create() {
        val betaManagedAgentsFileRubric =
            BetaManagedAgentsFileRubric.builder()
                .fileId("file_id")
                .type(BetaManagedAgentsFileRubric.Type.FILE)
                .build()

        assertThat(betaManagedAgentsFileRubric.fileId()).isEqualTo("file_id")
        assertThat(betaManagedAgentsFileRubric.type())
            .isEqualTo(BetaManagedAgentsFileRubric.Type.FILE)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsFileRubric =
            BetaManagedAgentsFileRubric.builder()
                .fileId("file_id")
                .type(BetaManagedAgentsFileRubric.Type.FILE)
                .build()

        val roundtrippedBetaManagedAgentsFileRubric =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsFileRubric),
                jacksonTypeRef<BetaManagedAgentsFileRubric>(),
            )

        assertThat(roundtrippedBetaManagedAgentsFileRubric).isEqualTo(betaManagedAgentsFileRubric)
    }
}
