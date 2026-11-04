package com.anthropic.models.organization.users

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class CannotRemoveLastMemberErrorDetailsTest {

    @Test
    fun create() {
        val cannotRemoveLastMemberErrorDetails =
            CannotRemoveLastMemberErrorDetails.builder().build()
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val cannotRemoveLastMemberErrorDetails =
            CannotRemoveLastMemberErrorDetails.builder().build()

        val roundtrippedCannotRemoveLastMemberErrorDetails =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(cannotRemoveLastMemberErrorDetails),
                jacksonTypeRef<CannotRemoveLastMemberErrorDetails>(),
            )

        assertThat(roundtrippedCannotRemoveLastMemberErrorDetails)
            .isEqualTo(cannotRemoveLastMemberErrorDetails)
    }
}
