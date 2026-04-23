// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.memorystores.memories

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsContentSha256PreconditionTest {

    @Test
    fun create() {
        val betaManagedAgentsContentSha256Precondition =
            BetaManagedAgentsContentSha256Precondition.builder()
                .type(BetaManagedAgentsContentSha256Precondition.Type.CONTENT_SHA256)
                .contentSha256("content_sha256")
                .build()

        assertThat(betaManagedAgentsContentSha256Precondition.type())
            .isEqualTo(BetaManagedAgentsContentSha256Precondition.Type.CONTENT_SHA256)
        assertThat(betaManagedAgentsContentSha256Precondition.contentSha256())
            .contains("content_sha256")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsContentSha256Precondition =
            BetaManagedAgentsContentSha256Precondition.builder()
                .type(BetaManagedAgentsContentSha256Precondition.Type.CONTENT_SHA256)
                .contentSha256("content_sha256")
                .build()

        val roundtrippedBetaManagedAgentsContentSha256Precondition =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsContentSha256Precondition),
                jacksonTypeRef<BetaManagedAgentsContentSha256Precondition>(),
            )

        assertThat(roundtrippedBetaManagedAgentsContentSha256Precondition)
            .isEqualTo(betaManagedAgentsContentSha256Precondition)
    }
}
