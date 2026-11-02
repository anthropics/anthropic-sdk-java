package com.anthropic.services.blocking.organization.rbacgroups

import com.anthropic.TestServerExtension
import com.anthropic.client.okhttp.AnthropicOkHttpClient
import com.anthropic.models.organization.rbacgroups.members.MemberAddParams
import com.anthropic.models.organization.rbacgroups.members.MemberRemoveParams
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(TestServerExtension::class)
internal class MemberServiceTest {

    private val client =
        AnthropicOkHttpClient.builder()
            .baseUrl(TestServerExtension.BASE_URL)
            .apiKey("my-anthropic-api-key")
            .build()

    @Test
    fun list() {
        val memberService = client.organization().rbacGroups().members()

        val page = memberService.list("rbac_group_id")

        page.response().validate()
    }

    @Test
    fun add() {
        val memberService = client.organization().rbacGroups().members()

        val rbacGroupMember =
            memberService.add(
                MemberAddParams.builder()
                    .rbacGroupId("rbac_group_id")
                    .userId("user_01WxYzAbCdEfGhJkMnPqRsTu")
                    .build()
            )

        rbacGroupMember.validate()
    }

    @Test
    fun remove() {
        val memberService = client.organization().rbacGroups().members()

        val deletedRbacGroupMember =
            memberService.remove(
                MemberRemoveParams.builder().rbacGroupId("rbac_group_id").userId("user_id").build()
            )

        deletedRbacGroupMember.validate()
    }
}
