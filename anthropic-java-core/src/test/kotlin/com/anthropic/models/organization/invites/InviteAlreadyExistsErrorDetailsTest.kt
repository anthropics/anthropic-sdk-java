package com.anthropic.models.organization.invites

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class InviteAlreadyExistsErrorDetailsTest {

    @Test
    fun create() {
        val inviteAlreadyExistsErrorDetails = InviteAlreadyExistsErrorDetails.builder().build()
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val inviteAlreadyExistsErrorDetails = InviteAlreadyExistsErrorDetails.builder().build()

        val roundtrippedInviteAlreadyExistsErrorDetails =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(inviteAlreadyExistsErrorDetails),
                jacksonTypeRef<InviteAlreadyExistsErrorDetails>(),
            )

        assertThat(roundtrippedInviteAlreadyExistsErrorDetails)
            .isEqualTo(inviteAlreadyExistsErrorDetails)
    }
}
