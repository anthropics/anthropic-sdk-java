package com.anthropic.services.blocking.organization.workspaces

import com.anthropic.TestServerExtension
import com.anthropic.client.okhttp.AnthropicOkHttpClient
import com.anthropic.models.organization.workspaces.NoBillingWorkspaceRole
import com.anthropic.models.organization.workspaces.WorkspaceRole
import com.anthropic.models.organization.workspaces.members.MemberAddParams
import com.anthropic.models.organization.workspaces.members.MemberRemoveParams
import com.anthropic.models.organization.workspaces.members.MemberRetrieveParams
import com.anthropic.models.organization.workspaces.members.MemberUpdateParams
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(TestServerExtension::class)
internal class MemberServiceTest {

    @Test
    fun retrieve() {
        val client =
            AnthropicOkHttpClient.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val memberService = client.organization().workspaces().members()

        val workspaceMember =
            memberService.retrieve(
                MemberRetrieveParams.builder().workspaceId("workspace_id").userId("user_id").build()
            )

        workspaceMember.validate()
    }

    @Test
    fun update() {
        val client =
            AnthropicOkHttpClient.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val memberService = client.organization().workspaces().members()

        val workspaceMember =
            memberService.update(
                MemberUpdateParams.builder()
                    .workspaceId("workspace_id")
                    .userId("user_id")
                    .workspaceRole(WorkspaceRole.WORKSPACE_ADMIN)
                    .build()
            )

        workspaceMember.validate()
    }

    @Test
    fun list() {
        val client =
            AnthropicOkHttpClient.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val memberService = client.organization().workspaces().members()

        val page = memberService.list("workspace_id")

        page.response().validate()
    }

    @Test
    fun add() {
        val client =
            AnthropicOkHttpClient.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val memberService = client.organization().workspaces().members()

        val workspaceMember =
            memberService.add(
                MemberAddParams.builder()
                    .workspaceId("workspace_id")
                    .userId("user_01WCz1FkmYMm4gnmykNKUu3Q")
                    .workspaceRole(NoBillingWorkspaceRole.WORKSPACE_ADMIN)
                    .build()
            )

        workspaceMember.validate()
    }

    @Test
    fun remove() {
        val client =
            AnthropicOkHttpClient.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val memberService = client.organization().workspaces().members()

        val member =
            memberService.remove(
                MemberRemoveParams.builder().workspaceId("workspace_id").userId("user_id").build()
            )

        member.validate()
    }
}
