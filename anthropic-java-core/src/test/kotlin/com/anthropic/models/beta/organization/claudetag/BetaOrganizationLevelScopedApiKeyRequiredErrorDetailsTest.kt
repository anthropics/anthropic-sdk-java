package com.anthropic.models.beta.organization.claudetag

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaOrganizationLevelScopedApiKeyRequiredErrorDetailsTest {

    @Test
    fun create() {
        val betaOrganizationLevelScopedApiKeyRequiredErrorDetails =
            BetaOrganizationLevelScopedApiKeyRequiredErrorDetails.builder().build()
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaOrganizationLevelScopedApiKeyRequiredErrorDetails =
            BetaOrganizationLevelScopedApiKeyRequiredErrorDetails.builder().build()

        val roundtrippedBetaOrganizationLevelScopedApiKeyRequiredErrorDetails =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(
                    betaOrganizationLevelScopedApiKeyRequiredErrorDetails
                ),
                jacksonTypeRef<BetaOrganizationLevelScopedApiKeyRequiredErrorDetails>(),
            )

        assertThat(roundtrippedBetaOrganizationLevelScopedApiKeyRequiredErrorDetails)
            .isEqualTo(betaOrganizationLevelScopedApiKeyRequiredErrorDetails)
    }
}
