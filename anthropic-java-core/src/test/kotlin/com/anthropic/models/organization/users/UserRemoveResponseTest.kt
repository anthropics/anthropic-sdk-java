package com.anthropic.models.organization.users

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class UserRemoveResponseTest {

    @Test
    fun create() {
        val userRemoveResponse = UserRemoveResponse.of("user_01WxYzAbCdEfGhJkMnPqRsTu")

        assertThat(userRemoveResponse.id()).isEqualTo("user_01WxYzAbCdEfGhJkMnPqRsTu")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val userRemoveResponse = UserRemoveResponse.of("user_01WxYzAbCdEfGhJkMnPqRsTu")

        val roundtrippedUserRemoveResponse =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(userRemoveResponse),
                jacksonTypeRef<UserRemoveResponse>(),
            )

        assertThat(roundtrippedUserRemoveResponse).isEqualTo(userRemoveResponse)
    }
}
