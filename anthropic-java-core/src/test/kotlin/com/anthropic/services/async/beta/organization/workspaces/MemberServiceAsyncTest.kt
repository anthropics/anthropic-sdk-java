// File generated from our OpenAPI spec by Stainless.

package com.anthropic.services.async.beta.organization.workspaces

import com.anthropic.TestServerExtension
import com.anthropic.client.okhttp.AnthropicOkHttpClientAsync
import com.anthropic.models.beta.organization.workspaces.BetaNoBillingWorkspaceRole
import com.anthropic.models.beta.organization.workspaces.BetaWorkspaceRole
import com.anthropic.models.beta.organization.workspaces.members.MemberAddParams
import com.anthropic.models.beta.organization.workspaces.members.MemberRemoveParams
import com.anthropic.models.beta.organization.workspaces.members.MemberRetrieveParams
import com.anthropic.models.beta.organization.workspaces.members.MemberUpdateParams
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
        val memberServiceAsync = client.beta().organization().workspaces().members()

        val betaWorkspaceMemberFuture =
            memberServiceAsync.retrieve(
                MemberRetrieveParams.builder().workspaceId("workspace_id").userId("user_id").build()
            )

        val betaWorkspaceMember = betaWorkspaceMemberFuture.get()
        betaWorkspaceMember.validate()
    }

    @Test
    fun update() {
        val client =
            AnthropicOkHttpClientAsync.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val memberServiceAsync = client.beta().organization().workspaces().members()

        val betaWorkspaceMemberFuture =
            memberServiceAsync.update(
                MemberUpdateParams.builder()
                    .workspaceId("workspace_id")
                    .userId("user_id")
                    .workspaceRole(BetaWorkspaceRole.WORKSPACE_ADMIN)
                    .build()
            )

        val betaWorkspaceMember = betaWorkspaceMemberFuture.get()
        betaWorkspaceMember.validate()
    }

    @Test
    fun list() {
        val client =
            AnthropicOkHttpClientAsync.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val memberServiceAsync = client.beta().organization().workspaces().members()

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
        val memberServiceAsync = client.beta().organization().workspaces().members()

        val betaWorkspaceMemberFuture =
            memberServiceAsync.add(
                MemberAddParams.builder()
                    .workspaceId("workspace_id")
                    .userId("user_01WCz1FkmYMm4gnmykNKUu3Q")
                    .workspaceRole(BetaNoBillingWorkspaceRole.WORKSPACE_ADMIN)
                    .build()
            )

        val betaWorkspaceMember = betaWorkspaceMemberFuture.get()
        betaWorkspaceMember.validate()
    }

    @Test
    fun remove() {
        val client =
            AnthropicOkHttpClientAsync.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val memberServiceAsync = client.beta().organization().workspaces().members()

        val memberFuture =
            memberServiceAsync.remove(
                MemberRemoveParams.builder().workspaceId("workspace_id").userId("user_id").build()
            )

        val member = memberFuture.get()
        member.validate()
    }
}
