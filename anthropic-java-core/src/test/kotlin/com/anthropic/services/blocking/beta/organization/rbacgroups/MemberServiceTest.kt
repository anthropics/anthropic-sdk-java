package com.anthropic.services.blocking.beta.organization.rbacgroups

import com.anthropic.TestServerExtension
import com.anthropic.client.okhttp.AnthropicOkHttpClient
import com.anthropic.models.beta.organization.rbacgroups.members.MemberAddParams
import com.anthropic.models.beta.organization.rbacgroups.members.MemberRemoveParams
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(TestServerExtension::class)
internal class MemberServiceTest {

    @Test
    fun list() {
        val client =
            AnthropicOkHttpClient.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val memberService = client.beta().organization().rbacGroups().members()

        val page = memberService.list("rbac_group_id")

        page.response().validate()
    }

    @Test
    fun add() {
        val client =
            AnthropicOkHttpClient.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val memberService = client.beta().organization().rbacGroups().members()

        val betaRbacGroupMember =
            memberService.add(
                MemberAddParams.builder()
                    .rbacGroupId("rbac_group_id")
                    .userId("user_01WCz1FkmYMm4gnmykNKUu3Q")
                    .build()
            )

        betaRbacGroupMember.validate()
    }

    @Test
    fun remove() {
        val client =
            AnthropicOkHttpClient.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val memberService = client.beta().organization().rbacGroups().members()

        val member =
            memberService.remove(
                MemberRemoveParams.builder().rbacGroupId("rbac_group_id").userId("user_id").build()
            )

        member.validate()
    }
}
