// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.organization.invites

import kotlin.jvm.optionals.getOrNull
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class InviteCreateParamsTest {

    @Test
    fun create() {
        InviteCreateParams.builder()
            .email("user@emaildomain.com")
            .role(InviteCreateParams.Role.USER)
            .addRbacGroupId("string")
            .build()
    }

    @Test
    fun body() {
        val params =
            InviteCreateParams.builder()
                .email("user@emaildomain.com")
                .role(InviteCreateParams.Role.USER)
                .addRbacGroupId("string")
                .build()

        val body = params._body()

        assertThat(body.email()).isEqualTo("user@emaildomain.com")
        assertThat(body.role()).isEqualTo(InviteCreateParams.Role.USER)
        assertThat(body.rbacGroupIds().getOrNull()).containsExactly("string")
    }

    @Test
    fun bodyWithoutOptionalFields() {
        val params =
            InviteCreateParams.builder()
                .email("user@emaildomain.com")
                .role(InviteCreateParams.Role.USER)
                .build()

        val body = params._body()

        assertThat(body.email()).isEqualTo("user@emaildomain.com")
        assertThat(body.role()).isEqualTo(InviteCreateParams.Role.USER)
    }
}
