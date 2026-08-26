// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.organization.users

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class UserRemoveResponseTest {

    @Test
    fun create() {
        val userRemoveResponse = UserRemoveResponse.of("user_01WCz1FkmYMm4gnmykNKUu3Q")

        assertThat(userRemoveResponse.id()).isEqualTo("user_01WCz1FkmYMm4gnmykNKUu3Q")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val userRemoveResponse = UserRemoveResponse.of("user_01WCz1FkmYMm4gnmykNKUu3Q")

        val roundtrippedUserRemoveResponse =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(userRemoveResponse),
                jacksonTypeRef<UserRemoveResponse>(),
            )

        assertThat(roundtrippedUserRemoveResponse).isEqualTo(userRemoveResponse)
    }
}
