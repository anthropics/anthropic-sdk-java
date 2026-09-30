package com.anthropic.models.organization.apikeys

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ApiKeyWorkspaceScopeTest {

    @Test
    fun create() {
        val apiKeyWorkspaceScope = ApiKeyWorkspaceScope.of("wrkspc_01JwQvzr7rXLA5AGx3HKfFUJ")

        assertThat(apiKeyWorkspaceScope.workspaceId()).isEqualTo("wrkspc_01JwQvzr7rXLA5AGx3HKfFUJ")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val apiKeyWorkspaceScope = ApiKeyWorkspaceScope.of("wrkspc_01JwQvzr7rXLA5AGx3HKfFUJ")

        val roundtrippedApiKeyWorkspaceScope =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(apiKeyWorkspaceScope),
                jacksonTypeRef<ApiKeyWorkspaceScope>(),
            )

        assertThat(roundtrippedApiKeyWorkspaceScope).isEqualTo(apiKeyWorkspaceScope)
    }
}
