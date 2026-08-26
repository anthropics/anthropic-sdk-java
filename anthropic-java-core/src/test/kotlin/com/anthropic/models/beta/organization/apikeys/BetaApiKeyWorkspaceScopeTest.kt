// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.organization.apikeys

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaApiKeyWorkspaceScopeTest {

    @Test
    fun create() {
        val betaApiKeyWorkspaceScope =
            BetaApiKeyWorkspaceScope.of("wrkspc_01JwQvzr7rXLA5AGx3HKfFUJ")

        assertThat(betaApiKeyWorkspaceScope.workspaceId())
            .isEqualTo("wrkspc_01JwQvzr7rXLA5AGx3HKfFUJ")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaApiKeyWorkspaceScope =
            BetaApiKeyWorkspaceScope.of("wrkspc_01JwQvzr7rXLA5AGx3HKfFUJ")

        val roundtrippedBetaApiKeyWorkspaceScope =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaApiKeyWorkspaceScope),
                jacksonTypeRef<BetaApiKeyWorkspaceScope>(),
            )

        assertThat(roundtrippedBetaApiKeyWorkspaceScope).isEqualTo(betaApiKeyWorkspaceScope)
    }
}
