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
                .contentSha256("210b9798eb53baa4e69d31c1071cf03d212b8ad0ca30cf321e0ea82e120aac26")
                .build()

        assertThat(betaManagedAgentsContentSha256Precondition.contentSha256())
            .contains("210b9798eb53baa4e69d31c1071cf03d212b8ad0ca30cf321e0ea82e120aac26")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsContentSha256Precondition =
            BetaManagedAgentsContentSha256Precondition.builder()
                .contentSha256("210b9798eb53baa4e69d31c1071cf03d212b8ad0ca30cf321e0ea82e120aac26")
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
