// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.dreams

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaDreamTest {

    @Test
    fun create() {
        val betaDream =
            BetaDream.builder()
                .id("id")
                .archivedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .createdAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .endedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .error(BetaDreamError.builder().message("message").type("type").build())
                .addMemoryStoreInput("x")
                .instructions("instructions")
                .model(
                    BetaDreamModelConfig.builder()
                        .id("x")
                        .speed(BetaDreamModelConfig.Speed.STANDARD)
                        .build()
                )
                .addMemoryStoreOutput("memory_store_id")
                .sessionId("session_id")
                .status(BetaDreamStatus.PENDING)
                .type(BetaDream.Type.DREAM)
                .usage(
                    BetaDreamUsage.builder()
                        .cacheCreationInputTokens(0)
                        .cacheReadInputTokens(0)
                        .inputTokens(0)
                        .outputTokens(0)
                        .build()
                )
                .build()

        assertThat(betaDream.id()).isEqualTo("id")
        assertThat(betaDream.archivedAt())
            .contains(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
        assertThat(betaDream.createdAt())
            .isEqualTo(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
        assertThat(betaDream.endedAt()).contains(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
        assertThat(betaDream.error())
            .contains(BetaDreamError.builder().message("message").type("type").build())
        assertThat(betaDream.inputs())
            .containsExactly(
                BetaDreamInput.ofMemoryStore(
                    BetaDreamMemoryStoreInput.builder()
                        .memoryStoreId("x")
                        .type(BetaDreamMemoryStoreInput.Type.MEMORY_STORE)
                        .build()
                )
            )
        assertThat(betaDream.instructions()).contains("instructions")
        assertThat(betaDream.model())
            .isEqualTo(
                BetaDreamModelConfig.builder()
                    .id("x")
                    .speed(BetaDreamModelConfig.Speed.STANDARD)
                    .build()
            )
        assertThat(betaDream.outputs())
            .containsExactly(
                BetaDreamOutput.builder()
                    .memoryStoreId("memory_store_id")
                    .type(BetaDreamOutput.Type.MEMORY_STORE)
                    .build()
            )
        assertThat(betaDream.sessionId()).contains("session_id")
        assertThat(betaDream.status()).isEqualTo(BetaDreamStatus.PENDING)
        assertThat(betaDream.type()).isEqualTo(BetaDream.Type.DREAM)
        assertThat(betaDream.usage())
            .isEqualTo(
                BetaDreamUsage.builder()
                    .cacheCreationInputTokens(0)
                    .cacheReadInputTokens(0)
                    .inputTokens(0)
                    .outputTokens(0)
                    .build()
            )
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaDream =
            BetaDream.builder()
                .id("id")
                .archivedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .createdAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .endedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .error(BetaDreamError.builder().message("message").type("type").build())
                .addMemoryStoreInput("x")
                .instructions("instructions")
                .model(
                    BetaDreamModelConfig.builder()
                        .id("x")
                        .speed(BetaDreamModelConfig.Speed.STANDARD)
                        .build()
                )
                .addMemoryStoreOutput("memory_store_id")
                .sessionId("session_id")
                .status(BetaDreamStatus.PENDING)
                .type(BetaDream.Type.DREAM)
                .usage(
                    BetaDreamUsage.builder()
                        .cacheCreationInputTokens(0)
                        .cacheReadInputTokens(0)
                        .inputTokens(0)
                        .outputTokens(0)
                        .build()
                )
                .build()

        val roundtrippedBetaDream =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaDream),
                jacksonTypeRef<BetaDream>(),
            )

        assertThat(roundtrippedBetaDream).isEqualTo(betaDream)
    }
}
