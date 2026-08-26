// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.organization.apikeys

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ApiKeyListPageResponseTest {

    @Test
    fun create() {
        val apiKeyListPageResponse =
            ApiKeyListPageResponse.builder()
                .addData(
                    BetaApiKey.builder()
                        .id("apikey_01Rj2N8SVvo6BePZj99NhmiT")
                        .createdAt(OffsetDateTime.parse("2024-10-30T23:58:27.427722Z"))
                        .createdBy(
                            BetaApiKeyCreatedBy.builder()
                                .id("user_01WCz1FkmYMm4gnmykNKUu3Q")
                                .type(BetaApiKeyCreatedBy.Type.USER)
                                .build()
                        )
                        .expiresAt(OffsetDateTime.parse("2024-10-30T23:58:27.427722Z"))
                        .name("Developer Key")
                        .partialKeyHint("sk-ant-api03-R2D...igAA")
                        .userActorPrincipal("user_01WCz1FkmYMm4gnmykNKUu3Q")
                        .workspaceScope("wrkspc_01JwQvzr7rXLA5AGx3HKfFUJ")
                        .status(BetaApiKey.Status.ACTIVE)
                        .workspaceId("wrkspc_01JwQvzr7rXLA5AGx3HKfFUJ")
                        .build()
                )
                .firstId("first_id")
                .hasMore(true)
                .lastId("last_id")
                .build()

        assertThat(apiKeyListPageResponse.data())
            .containsExactly(
                BetaApiKey.builder()
                    .id("apikey_01Rj2N8SVvo6BePZj99NhmiT")
                    .createdAt(OffsetDateTime.parse("2024-10-30T23:58:27.427722Z"))
                    .createdBy(
                        BetaApiKeyCreatedBy.builder()
                            .id("user_01WCz1FkmYMm4gnmykNKUu3Q")
                            .type(BetaApiKeyCreatedBy.Type.USER)
                            .build()
                    )
                    .expiresAt(OffsetDateTime.parse("2024-10-30T23:58:27.427722Z"))
                    .name("Developer Key")
                    .partialKeyHint("sk-ant-api03-R2D...igAA")
                    .userActorPrincipal("user_01WCz1FkmYMm4gnmykNKUu3Q")
                    .workspaceScope("wrkspc_01JwQvzr7rXLA5AGx3HKfFUJ")
                    .status(BetaApiKey.Status.ACTIVE)
                    .workspaceId("wrkspc_01JwQvzr7rXLA5AGx3HKfFUJ")
                    .build()
            )
        assertThat(apiKeyListPageResponse.firstId()).contains("first_id")
        assertThat(apiKeyListPageResponse.hasMore()).isEqualTo(true)
        assertThat(apiKeyListPageResponse.lastId()).contains("last_id")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val apiKeyListPageResponse =
            ApiKeyListPageResponse.builder()
                .addData(
                    BetaApiKey.builder()
                        .id("apikey_01Rj2N8SVvo6BePZj99NhmiT")
                        .createdAt(OffsetDateTime.parse("2024-10-30T23:58:27.427722Z"))
                        .createdBy(
                            BetaApiKeyCreatedBy.builder()
                                .id("user_01WCz1FkmYMm4gnmykNKUu3Q")
                                .type(BetaApiKeyCreatedBy.Type.USER)
                                .build()
                        )
                        .expiresAt(OffsetDateTime.parse("2024-10-30T23:58:27.427722Z"))
                        .name("Developer Key")
                        .partialKeyHint("sk-ant-api03-R2D...igAA")
                        .userActorPrincipal("user_01WCz1FkmYMm4gnmykNKUu3Q")
                        .workspaceScope("wrkspc_01JwQvzr7rXLA5AGx3HKfFUJ")
                        .status(BetaApiKey.Status.ACTIVE)
                        .workspaceId("wrkspc_01JwQvzr7rXLA5AGx3HKfFUJ")
                        .build()
                )
                .firstId("first_id")
                .hasMore(true)
                .lastId("last_id")
                .build()

        val roundtrippedApiKeyListPageResponse =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(apiKeyListPageResponse),
                jacksonTypeRef<ApiKeyListPageResponse>(),
            )

        assertThat(roundtrippedApiKeyListPageResponse).isEqualTo(apiKeyListPageResponse)
    }
}
