package com.anthropic.errors

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class NoCredentialsExceptionTest {

    @Test
    fun detail_whenSet_isPresent() {
        val source = CredentialSource("profile", CredentialSourceState.LOAD_FAILED, "not a file")

        assertThat(source.detail()).hasValue("not a file")
    }

    @Test
    fun detail_whenNotSet_isEmpty() {
        val source = CredentialSource("profile", CredentialSourceState.NOT_FOUND)

        assertThat(source.detail()).isEmpty
    }

    @Test
    fun message_sourceWithDetail_showsDetailInParentheses() {
        val source = CredentialSource("profile", CredentialSourceState.LOAD_FAILED, "not a file")

        val exception = NoCredentialsException(listOf(source))

        assertThat(exception).hasMessageContaining("- profile: LOAD_FAILED (not a file)\n")
    }

    @Test
    fun message_sourceWithoutDetail_endsLineAfterState() {
        val source = CredentialSource("profile", CredentialSourceState.NOT_FOUND)

        val exception = NoCredentialsException(listOf(source))

        assertThat(exception).hasMessageContaining("- profile: NOT_FOUND\n")
    }
}
