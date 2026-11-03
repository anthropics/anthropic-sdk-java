package com.anthropic.models.organization.apikeys

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class OrgApiKeyLimitExceededErrorDetailsTest {

    @Test
    fun create() {
        val orgApiKeyLimitExceededErrorDetails =
            OrgApiKeyLimitExceededErrorDetails.builder().build()
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val orgApiKeyLimitExceededErrorDetails =
            OrgApiKeyLimitExceededErrorDetails.builder().build()

        val roundtrippedOrgApiKeyLimitExceededErrorDetails =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(orgApiKeyLimitExceededErrorDetails),
                jacksonTypeRef<OrgApiKeyLimitExceededErrorDetails>(),
            )

        assertThat(roundtrippedOrgApiKeyLimitExceededErrorDetails)
            .isEqualTo(orgApiKeyLimitExceededErrorDetails)
    }
}
