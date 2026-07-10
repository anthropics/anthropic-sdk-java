// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.dreams

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class DreamListPageResponseTest {

    @Test
    fun create() {
        val dreamListPageResponse =
            DreamListPageResponse.builder()
                .addData(
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
                )
                .nextPage("next_page")
                .build()

        assertThat(dreamListPageResponse.data())
            .containsExactly(
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
            )
        assertThat(dreamListPageResponse.nextPage()).contains("next_page")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val dreamListPageResponse =
            DreamListPageResponse.builder()
                .addData(
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
                )
                .nextPage("next_page")
                .build()

        val roundtrippedDreamListPageResponse =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(dreamListPageResponse),
                jacksonTypeRef<DreamListPageResponse>(),
            )

        assertThat(roundtrippedDreamListPageResponse).isEqualTo(dreamListPageResponse)
    }
}
