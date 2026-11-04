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
                .contentSha256("210b9798eb53baa4e69d31c1071cf03d212b8ad0ca30cf321e0ea82e120aac26")
                .build()

        assertThat(betaManagedAgentsPrecondition.contentSha256())
            .contains("210b9798eb53baa4e69d31c1071cf03d212b8ad0ca30cf321e0ea82e120aac26")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsPrecondition =
            BetaManagedAgentsPrecondition.builder()
                .contentSha256("210b9798eb53baa4e69d31c1071cf03d212b8ad0ca30cf321e0ea82e120aac26")
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
