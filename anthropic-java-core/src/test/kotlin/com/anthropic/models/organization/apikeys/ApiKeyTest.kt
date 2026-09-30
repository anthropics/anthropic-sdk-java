package com.anthropic.models.organization.apikeys

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ApiKeyTest {

    @Test
    fun create() {
        val apiKey =
            ApiKey.builder()
                .id("apikey_01Rj2N8SVvo6BePZj99NhmiT")
                .createdAt(OffsetDateTime.parse("2024-10-30T23:58:27.427722Z"))
                .createdBy(
                    ApiKeyCreatedBy.builder()
                        .id("user_01WCz1FkmYMm4gnmykNKUu3Q")
                        .type(ApiKeyCreatedBy.Type.USER)
                        .build()
                )
                .expiresAt(OffsetDateTime.parse("2024-10-30T23:58:27.427722Z"))
                .name("Developer Key")
                .partialKeyHint("sk-ant-api03-R2D...igAA")
                .userActorPrincipal("user_01WCz1FkmYMm4gnmykNKUu3Q")
                .workspaceScope("wrkspc_01JwQvzr7rXLA5AGx3HKfFUJ")
                .status(ApiKey.Status.ACTIVE)
                .build()

        assertThat(apiKey.id()).isEqualTo("apikey_01Rj2N8SVvo6BePZj99NhmiT")
        assertThat(apiKey.createdAt())
            .isEqualTo(OffsetDateTime.parse("2024-10-30T23:58:27.427722Z"))
        assertThat(apiKey.createdBy())
            .contains(
                ApiKeyCreatedBy.builder()
                    .id("user_01WCz1FkmYMm4gnmykNKUu3Q")
                    .type(ApiKeyCreatedBy.Type.USER)
                    .build()
            )
        assertThat(apiKey.expiresAt()).contains(OffsetDateTime.parse("2024-10-30T23:58:27.427722Z"))
        assertThat(apiKey.name()).isEqualTo("Developer Key")
        assertThat(apiKey.partialKeyHint()).contains("sk-ant-api03-R2D...igAA")
        assertThat(apiKey.principal())
            .contains(ApiKey.Principal.ofUserActor("user_01WCz1FkmYMm4gnmykNKUu3Q"))
        assertThat(apiKey.scope())
            .isEqualTo(ApiKey.Scope.ofWorkspace("wrkspc_01JwQvzr7rXLA5AGx3HKfFUJ"))
        assertThat(apiKey.status()).isEqualTo(ApiKey.Status.ACTIVE)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val apiKey =
            ApiKey.builder()
                .id("apikey_01Rj2N8SVvo6BePZj99NhmiT")
                .createdAt(OffsetDateTime.parse("2024-10-30T23:58:27.427722Z"))
                .createdBy(
                    ApiKeyCreatedBy.builder()
                        .id("user_01WCz1FkmYMm4gnmykNKUu3Q")
                        .type(ApiKeyCreatedBy.Type.USER)
                        .build()
                )
                .expiresAt(OffsetDateTime.parse("2024-10-30T23:58:27.427722Z"))
                .name("Developer Key")
                .partialKeyHint("sk-ant-api03-R2D...igAA")
                .userActorPrincipal("user_01WCz1FkmYMm4gnmykNKUu3Q")
                .workspaceScope("wrkspc_01JwQvzr7rXLA5AGx3HKfFUJ")
                .status(ApiKey.Status.ACTIVE)
                .build()

        val roundtrippedApiKey =
            jsonMapper.readValue(jsonMapper.writeValueAsString(apiKey), jacksonTypeRef<ApiKey>())

        assertThat(roundtrippedApiKey).isEqualTo(apiKey)
    }
}
