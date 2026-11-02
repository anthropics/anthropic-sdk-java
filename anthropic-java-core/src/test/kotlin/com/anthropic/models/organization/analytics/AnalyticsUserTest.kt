package com.anthropic.models.organization.analytics

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class AnalyticsUserTest {

    @Test
    fun create() {
        val analyticsUser = AnalyticsUser.builder().id("id").emailAddress("email_address").build()

        assertThat(analyticsUser.id()).isEqualTo("id")
        assertThat(analyticsUser.emailAddress()).isEqualTo("email_address")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val analyticsUser = AnalyticsUser.builder().id("id").emailAddress("email_address").build()

        val roundtrippedAnalyticsUser =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(analyticsUser),
                jacksonTypeRef<AnalyticsUser>(),
            )

        assertThat(roundtrippedAnalyticsUser).isEqualTo(analyticsUser)
    }
}
