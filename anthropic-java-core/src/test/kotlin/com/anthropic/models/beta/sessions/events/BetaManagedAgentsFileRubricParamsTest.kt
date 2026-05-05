// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.sessions.events

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsFileRubricParamsTest {

    @Test
    fun create() {
        val betaManagedAgentsFileRubricParams =
            BetaManagedAgentsFileRubricParams.builder()
                .fileId("file_011CNha8iCJcU1wXNR6q4V8w")
                .type(BetaManagedAgentsFileRubricParams.Type.FILE)
                .build()

        assertThat(betaManagedAgentsFileRubricParams.fileId())
            .isEqualTo("file_011CNha8iCJcU1wXNR6q4V8w")
        assertThat(betaManagedAgentsFileRubricParams.type())
            .isEqualTo(BetaManagedAgentsFileRubricParams.Type.FILE)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsFileRubricParams =
            BetaManagedAgentsFileRubricParams.builder()
                .fileId("file_011CNha8iCJcU1wXNR6q4V8w")
                .type(BetaManagedAgentsFileRubricParams.Type.FILE)
                .build()

        val roundtrippedBetaManagedAgentsFileRubricParams =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsFileRubricParams),
                jacksonTypeRef<BetaManagedAgentsFileRubricParams>(),
            )

        assertThat(roundtrippedBetaManagedAgentsFileRubricParams)
            .isEqualTo(betaManagedAgentsFileRubricParams)
    }
}
