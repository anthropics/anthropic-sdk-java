package com.anthropic.models.organization.invites

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class OrgMemberLimitExceededErrorDetailsTest {

    @Test
    fun create() {
        val orgMemberLimitExceededErrorDetails =
            OrgMemberLimitExceededErrorDetails.builder().build()
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val orgMemberLimitExceededErrorDetails =
            OrgMemberLimitExceededErrorDetails.builder().build()

        val roundtrippedOrgMemberLimitExceededErrorDetails =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(orgMemberLimitExceededErrorDetails),
                jacksonTypeRef<OrgMemberLimitExceededErrorDetails>(),
            )

        assertThat(roundtrippedOrgMemberLimitExceededErrorDetails)
            .isEqualTo(orgMemberLimitExceededErrorDetails)
    }
}
