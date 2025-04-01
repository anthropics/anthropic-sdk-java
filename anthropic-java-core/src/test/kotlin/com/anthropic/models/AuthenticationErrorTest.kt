// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class AuthenticationErrorTest {

    @Test
    fun create() {
        val authenticationError = AuthenticationError.builder().message("message").build()

        assertThat(authenticationError.message()).isEqualTo("message")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val authenticationError = AuthenticationError.builder().message("message").build()

        val roundtrippedAuthenticationError =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(authenticationError),
                jacksonTypeRef<AuthenticationError>(),
            )

        assertThat(roundtrippedAuthenticationError).isEqualTo(authenticationError)
    }
}
