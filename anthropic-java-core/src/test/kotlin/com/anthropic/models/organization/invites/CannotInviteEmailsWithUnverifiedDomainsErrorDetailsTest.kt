package com.anthropic.models.organization.invites

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class CannotInviteEmailsWithUnverifiedDomainsErrorDetailsTest {

    @Test
    fun create() {
        val cannotInviteEmailsWithUnverifiedDomainsErrorDetails =
            CannotInviteEmailsWithUnverifiedDomainsErrorDetails.builder().build()
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val cannotInviteEmailsWithUnverifiedDomainsErrorDetails =
            CannotInviteEmailsWithUnverifiedDomainsErrorDetails.builder().build()

        val roundtrippedCannotInviteEmailsWithUnverifiedDomainsErrorDetails =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(cannotInviteEmailsWithUnverifiedDomainsErrorDetails),
                jacksonTypeRef<CannotInviteEmailsWithUnverifiedDomainsErrorDetails>(),
            )

        assertThat(roundtrippedCannotInviteEmailsWithUnverifiedDomainsErrorDetails)
            .isEqualTo(cannotInviteEmailsWithUnverifiedDomainsErrorDetails)
    }
}
