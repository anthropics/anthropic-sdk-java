// File generated from our OpenAPI spec by Stainless.

package com.anthropic.services.async.beta.organization

import com.anthropic.TestServerExtension
import com.anthropic.client.okhttp.AnthropicOkHttpClientAsync
import com.anthropic.models.beta.organization.invites.InviteCreateParams
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(TestServerExtension::class)
internal class InviteServiceAsyncTest {

    @Test
    fun create() {
        val client =
            AnthropicOkHttpClientAsync.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val inviteServiceAsync = client.beta().organization().invites()

        val betaOrganizationInviteFuture =
            inviteServiceAsync.create(
                InviteCreateParams.builder()
                    .email("user@emaildomain.com")
                    .role(InviteCreateParams.Role.USER)
                    .addRbacGroupId("string")
                    .build()
            )

        val betaOrganizationInvite = betaOrganizationInviteFuture.get()
        betaOrganizationInvite.validate()
    }

    @Test
    fun retrieve() {
        val client =
            AnthropicOkHttpClientAsync.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val inviteServiceAsync = client.beta().organization().invites()

        val betaOrganizationInviteFuture = inviteServiceAsync.retrieve("invite_id")

        val betaOrganizationInvite = betaOrganizationInviteFuture.get()
        betaOrganizationInvite.validate()
    }

    @Test
    fun list() {
        val client =
            AnthropicOkHttpClientAsync.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val inviteServiceAsync = client.beta().organization().invites()

        val pageFuture = inviteServiceAsync.list()

        val page = pageFuture.get()
        page.response().validate()
    }

    @Test
    fun delete() {
        val client =
            AnthropicOkHttpClientAsync.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val inviteServiceAsync = client.beta().organization().invites()

        val inviteFuture = inviteServiceAsync.delete("invite_id")

        val invite = inviteFuture.get()
        invite.validate()
    }
}
