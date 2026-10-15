package com.anthropic.services.async.organization

import com.anthropic.TestServerExtension
import com.anthropic.client.okhttp.AnthropicOkHttpClientAsync
import com.anthropic.models.organization.invites.InviteCreateParams
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
        val inviteServiceAsync = client.organization().invites()

        val organizationInviteFuture =
            inviteServiceAsync.create(
                InviteCreateParams.builder()
                    .email("user@emaildomain.com")
                    .role(InviteCreateParams.Role.USER)
                    .addRbacGroupId("string")
                    .build()
            )

        val organizationInvite = organizationInviteFuture.get()
        organizationInvite.validate()
    }

    @Test
    fun retrieve() {
        val inviteServiceAsync = client.organization().invites()

        val organizationInviteFuture = inviteServiceAsync.retrieve("invite_id")

        val organizationInvite = organizationInviteFuture.get()
        organizationInvite.validate()
    }

    @Test
    fun list() {
        val inviteServiceAsync = client.organization().invites()

        val pageFuture = inviteServiceAsync.list()

        val page = pageFuture.get()
        page.response().validate()
    }

    @Test
    fun delete() {
        val inviteServiceAsync = client.organization().invites()

        val inviteFuture = inviteServiceAsync.delete("invite_id")

        val invite = inviteFuture.get()
        invite.validate()
    }
}
