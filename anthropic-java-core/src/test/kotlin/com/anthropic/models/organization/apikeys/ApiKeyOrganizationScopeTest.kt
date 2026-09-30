package com.anthropic.models.organization.apikeys

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ApiKeyOrganizationScopeTest {

    @Test
    fun create() {
        val apiKeyOrganizationScope = ApiKeyOrganizationScope.builder().build()
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val apiKeyOrganizationScope = ApiKeyOrganizationScope.builder().build()

        val roundtrippedApiKeyOrganizationScope =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(apiKeyOrganizationScope),
                jacksonTypeRef<ApiKeyOrganizationScope>(),
            )

        assertThat(roundtrippedApiKeyOrganizationScope).isEqualTo(apiKeyOrganizationScope)
    }
}
