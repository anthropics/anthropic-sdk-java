package com.anthropic.services.async.beta.organization

import com.anthropic.TestServerExtension
import com.anthropic.client.okhttp.AnthropicOkHttpClientAsync
import com.anthropic.models.beta.organization.invites.InviteCreateParams
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(TestServerExtension::class)
internal class InviteServiceAsyncTest {

    private val client =
        AnthropicOkHttpClientAsync.builder()
            .baseUrl(TestServerExtension.BASE_URL)
            .apiKey("my-anthropic-api-key")
            .build()

    @Test
    fun create() {
        val inviteServiceAsync =
            client.beta().organization().invites().withOptions { it.putQueryParam("beta", "true") }

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
        val inviteServiceAsync =
            client.beta().organization().invites().withOptions { it.putQueryParam("beta", "true") }

        val betaOrganizationInviteFuture = inviteServiceAsync.retrieve("invite_id")

        val betaOrganizationInvite = betaOrganizationInviteFuture.get()
        betaOrganizationInvite.validate()
    }

    @Test
    fun list() {
        val inviteServiceAsync =
            client.beta().organization().invites().withOptions { it.putQueryParam("beta", "true") }

        val pageFuture = inviteServiceAsync.list()

        val page = pageFuture.get()
        page.response().validate()
    }

    @Test
    fun delete() {
        val inviteServiceAsync =
            client.beta().organization().invites().withOptions { it.putQueryParam("beta", "true") }

        val inviteFuture = inviteServiceAsync.delete("invite_id")

        val invite = inviteFuture.get()
        invite.validate()
    }
}
