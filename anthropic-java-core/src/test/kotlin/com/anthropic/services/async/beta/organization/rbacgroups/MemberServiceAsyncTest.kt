package com.anthropic.services.async.beta.organization.rbacgroups

import com.anthropic.TestServerExtension
import com.anthropic.client.okhttp.AnthropicOkHttpClientAsync
import com.anthropic.models.beta.organization.rbacgroups.members.MemberAddParams
import com.anthropic.models.beta.organization.rbacgroups.members.MemberRemoveParams
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(TestServerExtension::class)
internal class MemberServiceAsyncTest {

    @Test
    fun list() {
        val client =
            AnthropicOkHttpClientAsync.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val memberServiceAsync = client.beta().organization().rbacGroups().members()

        val pageFuture = memberServiceAsync.list("rbac_group_id")

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
        val memberServiceAsync = client.beta().organization().rbacGroups().members()

        val betaRbacGroupMemberFuture =
            memberServiceAsync.add(
                MemberAddParams.builder()
                    .rbacGroupId("rbac_group_id")
                    .userId("user_01WCz1FkmYMm4gnmykNKUu3Q")
                    .build()
            )

        val betaRbacGroupMember = betaRbacGroupMemberFuture.get()
        betaRbacGroupMember.validate()
    }

    @Test
    fun remove() {
        val client =
            AnthropicOkHttpClientAsync.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val memberServiceAsync = client.beta().organization().rbacGroups().members()

        val memberFuture =
            memberServiceAsync.remove(
                MemberRemoveParams.builder().rbacGroupId("rbac_group_id").userId("user_id").build()
            )

        val member = memberFuture.get()
        member.validate()
    }
}
