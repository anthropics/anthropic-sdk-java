package com.anthropic.services.blocking.organization

import com.anthropic.TestServerExtension
import com.anthropic.client.okhttp.AnthropicOkHttpClient
import com.anthropic.models.organization.invites.InviteCreateParams
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(TestServerExtension::class)
internal class InviteServiceTest {

    private val client =
        AnthropicOkHttpClient.builder()
            .baseUrl(TestServerExtension.BASE_URL)
            .apiKey("my-anthropic-api-key")
            .build()

    @Test
    fun create() {
        val inviteService = client.organization().invites()

        val organizationInvite =
            inviteService.create(
                InviteCreateParams.builder()
                    .email("user@emaildomain.com")
                    .role(InviteCreateParams.Role.USER)
                    .addRbacGroupId("string")
                    .build()
            )

        organizationInvite.validate()
    }

    @Test
    fun retrieve() {
        val inviteService = client.organization().invites()

        val organizationInvite = inviteService.retrieve("invite_id")

        organizationInvite.validate()
    }

    @Test
    fun list() {
        val inviteService = client.organization().invites()

        val page = inviteService.list()

        page.response().validate()
    }

    @Test
    fun delete() {
        val inviteService = client.organization().invites()

        val invite = inviteService.delete("invite_id")

        invite.validate()
    }
}
