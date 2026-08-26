// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.organization.apikeys

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaApiKeyOrganizationScopeTest {

    @Test
    fun create() {
        val betaApiKeyOrganizationScope = BetaApiKeyOrganizationScope.builder().build()
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaApiKeyOrganizationScope = BetaApiKeyOrganizationScope.builder().build()

        val roundtrippedBetaApiKeyOrganizationScope =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaApiKeyOrganizationScope),
                jacksonTypeRef<BetaApiKeyOrganizationScope>(),
            )

        assertThat(roundtrippedBetaApiKeyOrganizationScope).isEqualTo(betaApiKeyOrganizationScope)
    }
}
