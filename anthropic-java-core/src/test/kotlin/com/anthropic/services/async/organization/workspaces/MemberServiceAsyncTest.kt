package com.anthropic.services.async.organization.workspaces

import com.anthropic.TestServerExtension
import com.anthropic.client.okhttp.AnthropicOkHttpClientAsync
import com.anthropic.models.organization.workspaces.NoBillingWorkspaceRole
import com.anthropic.models.organization.workspaces.WorkspaceRole
import com.anthropic.models.organization.workspaces.members.MemberAddParams
import com.anthropic.models.organization.workspaces.members.MemberRemoveParams
import com.anthropic.models.organization.workspaces.members.MemberRetrieveParams
import com.anthropic.models.organization.workspaces.members.MemberUpdateParams
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(TestServerExtension::class)
internal class MemberServiceAsyncTest {

    @Test
    fun retrieve() {
        val client =
            AnthropicOkHttpClientAsync.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val memberServiceAsync = client.organization().workspaces().members()

        val workspaceMemberFuture =
            memberServiceAsync.retrieve(
                MemberRetrieveParams.builder().workspaceId("workspace_id").userId("user_id").build()
            )

        val workspaceMember = workspaceMemberFuture.get()
        workspaceMember.validate()
    }

    @Test
    fun update() {
        val client =
            AnthropicOkHttpClientAsync.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val memberServiceAsync = client.organization().workspaces().members()

        val workspaceMemberFuture =
            memberServiceAsync.update(
                MemberUpdateParams.builder()
                    .workspaceId("workspace_id")
                    .userId("user_id")
                    .workspaceRole(WorkspaceRole.WORKSPACE_ADMIN)
                    .build()
            )

        val workspaceMember = workspaceMemberFuture.get()
        workspaceMember.validate()
    }

    @Test
    fun list() {
        val client =
            AnthropicOkHttpClientAsync.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val memberServiceAsync = client.organization().workspaces().members()

        val pageFuture = memberServiceAsync.list("workspace_id")

        val page = pageFuture.get()
        page.response().validate()
    }

    @Test
    fun add() {
        val client =
            AnthropicOkHttpClientAsync.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val memberServiceAsync = client.organization().workspaces().members()

        val workspaceMemberFuture =
            memberServiceAsync.add(
                MemberAddParams.builder()
                    .workspaceId("workspace_id")
                    .userId("user_01WCz1FkmYMm4gnmykNKUu3Q")
                    .workspaceRole(NoBillingWorkspaceRole.WORKSPACE_ADMIN)
                    .build()
            )

        val workspaceMember = workspaceMemberFuture.get()
        workspaceMember.validate()
    }

    @Test
    fun remove() {
        val client =
            AnthropicOkHttpClientAsync.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val memberServiceAsync = client.organization().workspaces().members()

        val memberFuture =
            memberServiceAsync.remove(
                MemberRemoveParams.builder().workspaceId("workspace_id").userId("user_id").build()
            )

        val member = memberFuture.get()
        member.validate()
    }
}
