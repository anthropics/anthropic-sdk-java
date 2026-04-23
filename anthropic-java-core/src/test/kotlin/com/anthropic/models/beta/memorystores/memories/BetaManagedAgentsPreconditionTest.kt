// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.memorystores.memories

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsPreconditionTest {

    @Test
    fun create() {
        val betaManagedAgentsPrecondition =
            BetaManagedAgentsPrecondition.builder()
                .type(BetaManagedAgentsPrecondition.Type.CONTENT_SHA256)
                .contentSha256("content_sha256")
                .build()

        assertThat(betaManagedAgentsPrecondition.type())
            .isEqualTo(BetaManagedAgentsPrecondition.Type.CONTENT_SHA256)
        assertThat(betaManagedAgentsPrecondition.contentSha256()).contains("content_sha256")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsPrecondition =
            BetaManagedAgentsPrecondition.builder()
                .type(BetaManagedAgentsPrecondition.Type.CONTENT_SHA256)
                .contentSha256("content_sha256")
                .build()

        val roundtrippedBetaManagedAgentsPrecondition =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsPrecondition),
                jacksonTypeRef<BetaManagedAgentsPrecondition>(),
            )

        assertThat(roundtrippedBetaManagedAgentsPrecondition)
            .isEqualTo(betaManagedAgentsPrecondition)
    }
}
