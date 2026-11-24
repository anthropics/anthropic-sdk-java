package com.anthropic.models.beta.organization.apikeys

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaApiKeyWorkspaceScopeTest {

    @Test
    fun create() {
        val betaApiKeyWorkspaceScope =
            BetaApiKeyWorkspaceScope.of("wrkspc_01AbCdEfGhJkLmNpQrStUvWx")

        assertThat(betaApiKeyWorkspaceScope.workspaceId())
            .isEqualTo("wrkspc_01AbCdEfGhJkLmNpQrStUvWx")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaApiKeyWorkspaceScope =
            BetaApiKeyWorkspaceScope.of("wrkspc_01AbCdEfGhJkLmNpQrStUvWx")

        val roundtrippedBetaApiKeyWorkspaceScope =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaApiKeyWorkspaceScope),
                jacksonTypeRef<BetaApiKeyWorkspaceScope>(),
            )

        assertThat(roundtrippedBetaApiKeyWorkspaceScope).isEqualTo(betaApiKeyWorkspaceScope)
    }
}
