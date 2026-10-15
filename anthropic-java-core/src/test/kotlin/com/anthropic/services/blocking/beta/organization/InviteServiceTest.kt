package com.anthropic.services.blocking.beta.organization

import com.anthropic.TestServerExtension
import com.anthropic.client.okhttp.AnthropicOkHttpClient
import com.anthropic.models.beta.organization.invites.InviteCreateParams
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
        val inviteService = client.beta().organization().invites()

        val betaOrganizationInvite =
            inviteService.create(
                InviteCreateParams.builder()
                    .email("user@emaildomain.com")
                    .role(InviteCreateParams.Role.USER)
                    .addRbacGroupId("string")
                    .build()
            )

        betaOrganizationInvite.validate()
    }

    @Test
    fun retrieve() {
        val inviteService = client.beta().organization().invites()

        val betaOrganizationInvite = inviteService.retrieve("invite_id")

        betaOrganizationInvite.validate()
    }

    @Test
    fun list() {
        val inviteService = client.beta().organization().invites()

        val page = inviteService.list()

        page.response().validate()
    }

    @Test
    fun delete() {
        val inviteService = client.beta().organization().invites()

        val invite = inviteService.delete("invite_id")

        invite.validate()
    }
}
