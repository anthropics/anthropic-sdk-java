package com.anthropic.models.organization.invites

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class EmailDomainNotAllowedErrorDetailsTest {

    @Test
    fun create() {
        val emailDomainNotAllowedErrorDetails = EmailDomainNotAllowedErrorDetails.builder().build()
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val emailDomainNotAllowedErrorDetails = EmailDomainNotAllowedErrorDetails.builder().build()

        val roundtrippedEmailDomainNotAllowedErrorDetails =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(emailDomainNotAllowedErrorDetails),
                jacksonTypeRef<EmailDomainNotAllowedErrorDetails>(),
            )

        assertThat(roundtrippedEmailDomainNotAllowedErrorDetails)
            .isEqualTo(emailDomainNotAllowedErrorDetails)
    }
}
