// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.organization.apikeys

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaApiKeyTest {

    @Test
    fun create() {
        val betaApiKey =
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

        assertThat(betaApiKey.id()).isEqualTo("apikey_01Rj2N8SVvo6BePZj99NhmiT")
        assertThat(betaApiKey.createdAt())
            .isEqualTo(OffsetDateTime.parse("2024-10-30T23:58:27.427722Z"))
        assertThat(betaApiKey.createdBy())
            .contains(
                BetaApiKeyCreatedBy.builder()
                    .id("user_01WCz1FkmYMm4gnmykNKUu3Q")
                    .type(BetaApiKeyCreatedBy.Type.USER)
                    .build()
            )
        assertThat(betaApiKey.expiresAt())
            .contains(OffsetDateTime.parse("2024-10-30T23:58:27.427722Z"))
        assertThat(betaApiKey.name()).isEqualTo("Developer Key")
        assertThat(betaApiKey.partialKeyHint()).contains("sk-ant-api03-R2D...igAA")
        assertThat(betaApiKey.principal())
            .contains(BetaApiKey.Principal.ofUserActor("user_01WCz1FkmYMm4gnmykNKUu3Q"))
        assertThat(betaApiKey.scope())
            .isEqualTo(BetaApiKey.Scope.ofWorkspace("wrkspc_01JwQvzr7rXLA5AGx3HKfFUJ"))
        assertThat(betaApiKey.status()).isEqualTo(BetaApiKey.Status.ACTIVE)
        assertThat(betaApiKey.workspaceId()).contains("wrkspc_01JwQvzr7rXLA5AGx3HKfFUJ")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaApiKey =
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

        val roundtrippedBetaApiKey =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaApiKey),
                jacksonTypeRef<BetaApiKey>(),
            )

        assertThat(roundtrippedBetaApiKey).isEqualTo(betaApiKey)
    }
}
