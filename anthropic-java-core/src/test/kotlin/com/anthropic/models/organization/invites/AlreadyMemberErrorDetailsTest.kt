package com.anthropic.models.organization.invites

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class AlreadyMemberErrorDetailsTest {

    @Test
    fun create() {
        val alreadyMemberErrorDetails = AlreadyMemberErrorDetails.builder().build()
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val alreadyMemberErrorDetails = AlreadyMemberErrorDetails.builder().build()

        val roundtrippedAlreadyMemberErrorDetails =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(alreadyMemberErrorDetails),
                jacksonTypeRef<AlreadyMemberErrorDetails>(),
            )

        assertThat(roundtrippedAlreadyMemberErrorDetails).isEqualTo(alreadyMemberErrorDetails)
    }
}
